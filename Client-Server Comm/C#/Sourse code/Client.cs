// using System.Net.Sockets;
// using System.Text;
//
// namespace Server_Client;
//
// public class Client2
// {
//     public static void Main(string[] args)
//     {
//         int totalClients = 3;
//         for (int i = 0; i < totalClients; i++)
//         {
//             int clientId = i;
//             Thread thread = new Thread(()=> RunClient(clientId));
//             thread.IsBackground = true;
//             thread.Start();
//             Thread.Sleep(1000);
//         }
//         Console.ReadKey();
//     }
//
//     static void RunClient(int clientId)
//     {
//         try
//         {
//             TcpClient  client = new TcpClient("127.0.0.1", 9000);
//             NetworkStream stream = client.GetStream();
//             StreamReader reader =  new StreamReader(stream, Encoding.UTF8);
//             int threadId = Thread.CurrentThread.ManagedThreadId;
//             Console.WriteLine($"Client # {clientId} Thread Id: {threadId}");
//
//             string line;
//             while ((line = reader.ReadLine()) != null)
//             {
//                 Console.WriteLine($"Client # {clientId} Received: {line}. Thread Id: {threadId}");
//             }
//
//             Console.WriteLine("Disconnected");
//         }
//         catch (Exception e)
//         {
//             Console.WriteLine($"Client # {clientId} Error. Reason: {e.Message}");
//             throw;
//         }
//     }
// }