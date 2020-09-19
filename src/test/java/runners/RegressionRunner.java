package runners;

import beans.db.Food;
import org.testng.annotations.Test;
import utils.db.DataBaseUtils;
import utils.io_utils.CSVWriter;
import utils.io_utils.CSVReader;
import java.io.IOException;
import java.sql.SQLException;

public class RegressionRunner {
//    @Test
////    public void t1() throws Exception, IOException {
////        DataBaseUtils.connectToDatabase();
////        CSVWriter writer = new CSVWriter("./foods.csv");
////        Food.getAll().forEach(x -> {
////            try{
////                writer.write(x.toCSVString());
////            }catch (IOException e){
////                e.printStackTrace();
////            }
////        });
////        writer.flush();
////        writer.close();
////    }

    @Test
    public void t2() throws IOException{
        CSVReader reader = new CSVReader("./foods.csv");
        String line;
        while ((line = reader.read()) != null){
            System.out.println(new Food(line));
        }
        reader.close();
    }
}
