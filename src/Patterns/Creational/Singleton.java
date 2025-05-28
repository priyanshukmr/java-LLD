package Patterns.Creational;

/*
Intent: Restrict instantiation of a class to one object.
Components:
- singleton
- client

Below code is not thread safe
 */


public class Singleton {

    private static Singleton dbConnection;

    private Singleton(){
        System.out.println("Creating db connection singleton");
    }

    public static Singleton getInstance() {
        if(dbConnection==null) {
            dbConnection = new Singleton();
            return dbConnection;
        }
        System.out.println("db already connected");
        return dbConnection;
    }


    public static void main(String[] args) {
        Singleton db = Singleton.getInstance();
        System.out.println("db connected");
        db = Singleton.getInstance();

        ThreadSafeLazySingleton singleton = ThreadSafeLazySingleton.getInstance();
    }





    // Thread-safe singleton, eager load (why its thread safe?)
    static class ThreadSafeSingleton {
        private static final ThreadSafeSingleton dbConnection = new ThreadSafeSingleton();

        private ThreadSafeSingleton() {
            System.out.println("creating singleton");
        }

        public ThreadSafeSingleton getInstance() {
            return dbConnection;
        }
    }


    // Thread safe singleton, lazy load (Bill Pugh singleton)
    static class ThreadSafeLazySingleton {

        private ThreadSafeLazySingleton() {
            System.out.println("creating thread safe singleton");
        }

        static class Holder {
            private static final ThreadSafeLazySingleton dbConnection = new ThreadSafeLazySingleton();
        }

        public static ThreadSafeLazySingleton getInstance() {
            return Holder.dbConnection;
        }
    }

}
