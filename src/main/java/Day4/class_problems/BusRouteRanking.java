package Day4.class_problems;

public class BusRouteRanking {

    static class BusRoute implements Comparable<BusRoute> {

        private String routeCode;
        private String routeName;
        private int totalBookings;
        private double onTimePercent;

        public BusRoute(
                String routeCode,
                String routeName,
                int totalBookings,
                double onTimePercent) {

            this.routeCode = routeCode;
            this.routeName = routeName;
            this.totalBookings = totalBookings;
            this.onTimePercent = onTimePercent;
        }

        @Override
        public int compareTo(BusRoute other) {

            // 1. Higher bookings first
            if (this.totalBookings != other.totalBookings) {
                return Integer.compare(
                    other.totalBookings,
                    this.totalBookings
                );
            }

            // 2. Higher on-time percentage first
            if (Double.compare(
                    this.onTimePercent,
                    other.onTimePercent) != 0) {

                return Double.compare(
                    other.onTimePercent,
                    this.onTimePercent
                );
            }

            // 3. Route code ascending
            return this.routeCode.compareTo(other.routeCode);
        }

        @Override
        public String toString() {
            return routeCode
                    + " | "
                    + routeName
                    + " | Bookings: "
                    + totalBookings
                    + " | On-time: "
                    + onTimePercent;
        }
    }

    // Stable manual insertion sort
    public static void stableSort(BusRoute[] routes) {

        for (int i = 1; i < routes.length; i++) {

            BusRoute current = routes[i];

            int j = i - 1;

            while (j >= 0
                    && routes[j].compareTo(current) > 0) {

                routes[j + 1] = routes[j];
                j--;
            }

            routes[j + 1] = current;
        }
    }

    public static void main(String[] args) {

        BusRoute[] routes = {

            new BusRoute(
                "R03",
                "Chennai-Bangalore",
                120,
                92.5
            ),

            new BusRoute(
                "R01",
                "Chennai-Madurai",
                150,
                88.0
            ),

            new BusRoute(
                "R02",
                "Chennai-Coimbatore",
                150,
                95.0
            ),

            new BusRoute(
                "R04",
                "Chennai-Trichy",
                120,
                92.5
            )
        };

        stableSort(routes);

        for (BusRoute route : routes) {
            System.out.println(route);
        }
    }
}