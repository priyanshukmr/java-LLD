package Patterns.Structural;

/*
Intent: provide simplified interface for complex subsystems

Components:
- Facade: single front facing class representing entire subsystem
- Subsystem classes: complex classes that facade hides from clients
- Client: interacts with facade only
 */

public class  Facade {


    // Subsystem classes
    static class CPU {
        void start() {
            System.out.println("CPU started");
        }
    }

    static class Memory {
        void load() {
            System.out.println("Memory loaded");
        }
    }

    static class Disk {
        void spin() {
            System.out.println("Disk spinning");
        }
    }


    // Facade: CPU, Memory and Disk
    static class Computer {
        private CPU cpu;
        private Memory memory;
        private Disk disk;

        Computer() {
            cpu = new CPU();
            memory = new Memory();
            disk = new Disk();
        }

        void start() {
            cpu.start();
            memory.load();
            disk.spin();
        }
    }

    //Client
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
    }


}
