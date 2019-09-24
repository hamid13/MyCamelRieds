package mycameltest;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;


/**
 * This is very simple test using Java DSL in Camel to move a file from local dri to another dir
 * In this example we have simple route local to local
 * we also have a simple processor
 */

public class LocalToLocal {

    public static void main(String[] args) throws Exception{

        CamelContext context = new DefaultCamelContext();

//      Properties component, setting a properties file
        PropertiesComponent prob = context.getComponent("properties",PropertiesComponent.class);
        prob.setLocation("classpath:properties.properties");


        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
//                System.out.println("sdg");
                    from("file:{{source}}?noop=true")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                System.out.println("Download completed "+ exchange.getIn().getHeader("CamelFileName"));
                            }
                        })
                        .to("file://{{destination}}");

            }
        });

        context.start();
        Thread.sleep(1000);
        context.stop();

    }
}
