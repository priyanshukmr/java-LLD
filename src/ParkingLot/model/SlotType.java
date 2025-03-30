package ParkingLot.model;

public enum SlotType {
    TwoWheeler{
        @Override
        public long getHourlyRate() {
            return 2;
        }
    }, FourWheeler {
        @Override
        public  long getHourlyRate() {
            return 4;
        }
    }, Large {
        public long getHourlyRate() {
            return 10;
        }
    };

    public abstract long getHourlyRate();
}
