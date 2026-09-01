/* Practical No. 5
   Create a multi-threaded Java application that simulates any real time application with required
   functionalities. For eg. Basic chat system in which each user (thread) sends and receives
   messages. Use isAlive() to check the status of threads and join() to ensure proper
   synchronization. Implement thread priorities to handle high-priority messages and demonstrate
   thread suspension, resumption, and stopping. */

class ChatUser extends Thread {
    private String userName;
    private String[] messages;
    private boolean paused = false;
    private boolean stopped = false;

    public ChatUser(String name, String[] messages) {
        this.userName = name;
        this.messages = messages;
    }

    public void pauseChat() {
        paused = true;
    }

    public void resumeChat() {
        paused = false;
        synchronized (this) {
            notify(); // Resume the thread if it was paused
        }
    }

    public void stopChat() {
        stopped = true;
    }

    public void run() {
        try {
            for (String message : messages) {
                if (stopped) {
                    System.out.println(userName + " has left the chat.");
                    break;
                }
                synchronized (this) {
                    while (paused) {
                        wait(); // Suspend the thread
                    }
                }
                System.out.println(userName + ": " + message);
                Thread.sleep(1000); // Simulate delay between messages
            }
        } catch (InterruptedException e) {
            System.out.println(userName + " was interrupted.");
        }
    }
}

// Test Class with Priorities, isAlive(), and join()
public class Practical5_ChatSimulation {
    public static void main(String[] args) {
        String[] messages1 = {"Hi!", "How are you?", "What are you doing today?"};
        String[] messages2 = {"Hello!", "I'm good, thanks!", "Let's go out!"};

        ChatUser user1 = new ChatUser("Alice", messages1);
        ChatUser user2 = new ChatUser("Bob", messages2);

        // Set priorities
        user1.setPriority(Thread.MIN_PRIORITY); // Low-priority messages
        user2.setPriority(Thread.MAX_PRIORITY); // High-priority messages

        user1.start();
        user2.start();

        // Check if threads are alive
        System.out.println("Is Alice alive? " + user1.isAlive());
        System.out.println("Is Bob alive? " + user2.isAlive());

        try {
            Thread.sleep(2000);
            System.out.println("\nPausing Bob's chat...");
            user2.pauseChat();

            Thread.sleep(3000);
            System.out.println("Resuming Bob's chat...\n");
            user2.resumeChat();

            Thread.sleep(2000);
            System.out.println("Stopping Alice's chat...\n");
            user1.stopChat();

            // Synchronize threads
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\nChat ended.");
    }
}
