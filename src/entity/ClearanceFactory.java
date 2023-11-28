package entity;

public class ClearanceFactory {

        /**

         * @param name
         * @param level
         * @return
         */


        public Clearance create(String name, Integer level, Key key) {
            return new Clearance(name, level, key);
        }

}
