package entity;

public class ClearanceFactory {

        /**

         * @param name
         * @param level
         * @return
         */


        public Clearance create(String name, Integer level) {
            return new Clearance(name, level);
        }

}
