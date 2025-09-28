// using System.Net;
// using System.Net.Sockets;
// using System.Text;
//
// namespace Project_Server;
//
// public class Server2
// {
//     public static void Main(string[] args)
//     {
//         TcpListener listener = new TcpListener(IPAddress.Any, 9000);
//         listener.Start();
//         Console.WriteLine("==== Server started ====");
//         while (true)
//         {
//             // 主 Thread 專門客戶端連線
//             TcpClient client = listener.AcceptTcpClient();
//             Console.WriteLine("==== Client accepted ====");
//             
//             // 為每一個 client 開 Thread
//             
//             // 法一 : 
//             //Thread clientThread = new Thread(HandleClient);
//             //clientThread.IsBackground = true;
//             // 法二 :
//             // Thread clientThread = new Thread(() => HandleClient(client));
//             // clientThread.IsBackground = true;
//             // 法三 :
//             Task.Run(() => HandleClient(client)); //預設為背景執行緒
//             
//             
//             //clientThread.Start(client);
//             
//             
//             
//             
//         }
//     }
//
//     public static void HandleClient(object obj)
//     {
//         TcpClient client = (TcpClient)obj;
//         try
//         {
//             NetworkStream stream = client.GetStream();
//             StreamWriter writer = new StreamWriter(stream, Encoding.UTF8)
//             {
//                 AutoFlush = true
//             };
//             Random random = new Random();
//
//             while (client.Connected)
//             {
//                 int value = random.Next(100);
//                 writer.WriteLine(value);
//                 Console.WriteLine($"[Client {Thread.CurrentThread.ManagedThreadId}]Sent : {value}");
//                 Thread.Sleep(1000);
//             }
//         }
//         catch (Exception e)
//         {
//             Console.WriteLine($"Client {Thread.CurrentThread.ManagedThreadId}  Error: {e.Message}");
//         }
//         finally
//         {
//             client.Close(); // 確保連線正常釋放
//             Console.WriteLine("==== Sever disconnected ====");
//         }
//     }
// }