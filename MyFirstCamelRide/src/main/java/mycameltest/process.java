package mycameltest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class process implements Processor {
    public void process(Exchange exchange) throws Exception {
        System.out.println("Downloading..." + exchange.getIn().getHeader("CamelFileName"));
    }
}
