package net.wonsi.test;

import lombok.SneakyThrows;
import lombok.val;
import net.wonsi.WonsiFactory;
import net.wonsi.api.Wonsi;
import net.wonsi.api.table.WonsiTable;
import net.wonsi.test.model.Car;
import net.wonsi.test.model.User;
import net.wonsi.test.mysql.MySqlUtil;
import net.wonsi.test.repo.UserRepo;

public class TemplateRepoBootstrap {

    @SneakyThrows
    public static void main(String[] args) {
       Wonsi wonsi = WonsiFactory.createInstance(MySqlUtil.create());
       WonsiTable<Car> table = wonsi.getTable(Car.class);

       val repository = table.createRepository(long.class);

//       repository.save(new Car(1, "BMW", "x3"));
//       repository.save(new Car(2, "BMW", "x5"));
//       repository.save(new Car(3, "BMW", "x7"));
//       repository.save(new Car(6, "BMW", "cla"));
//
//       repository.save(new Car(9, "Porche", "911"));
//       repository.save(new Car(11, "Mersedes-Benz", "GLA"));

       System.out.println("[Test-Select] Model from row where id = 2: " + repository.findByIdentifier(2L).get().getModel());

       System.out.println("[Find-All-Test] Items:");
       for (val car: repository.findAll())
            System.out.println(String.format("  #%s %s %s", car.getId(), car.getConcern(), car.getModel()));

       System.out.println("[Delete-Test] Trying to delete item with id=6");
       repository.deleteByIdentifier(6L);
    }
}
