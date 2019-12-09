
ublic class TestA<test> extends CamelSpringTestSupport {

    public TestA() {
    }

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("camel-context.xml");
    }

    private HttpEntity requestEntity;

    private String tokenResponse = "placeholder";

    private HttpEntity setUpMultipartForm() throws IOException {}

    public static class MultipartParser {}

    private static List getResponseAsList(String response) {}

    private MockEndpoint mockOut;

    @BeforeEach
    public void init() throws Exception {

        mockOut = getMockEndpoint("mock:result");

        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("seda:getToken").transform(constant(tokenResponse));
                from("seda:saveMessage")
                        .bean(MultipartParser.class, "parse")
                        .to(mockOut);
            }
        });
        requestEntity = setUpMultipartForm();
        template.sendBody("direct:start", requestEntity.getContent());
    }

    @Test
    public void shouldDoThis() throws Exception {
        mockOut.expects(new Runnable() {
            public void run() {
                List response = getResponseAsList((String) mockOut.getExchanges().get(0).getIn().getBody());
                for (Object o : response) {
                    HashMap hashMap = (HashMap) o;
                    String trueValue = (String) hashMap.get("placeholder");
                    String expected = trueValue.toUpperCase();
                    assertEquals(expected, trueValue);
                }
            }
        });
        mockOut.setResultWaitTime(10000);
        mockOut.expectedMessageCount(1);
        mockOut.assertIsSatisfied();
    }

        @Test
    public void shouldDoThis2() throws Exception {
        mockOut.expects(new Runnable() {
            public void run() {
                List response = getResponseAsList((String) mockOut.getExchanges().get(0).getIn().getBody());
                for (Object o : response) {
                    HashMap hashMap = (HashMap) o;
                    String trueValue= (String) hashMap.get("placeholder");
                    String expected = trueValue.toUpperCase();
                    assertEquals(expected, trueValue);
                }
            }
        });
        mockOut.setResultWaitTime(10000);
        mockOut.expectedMessageCount(1);
        mockOut.assertIsSatisfied();
    }

}
