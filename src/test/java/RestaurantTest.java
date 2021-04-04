import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
     @BeforeEach
     public void should_run_before_each_test_case() {
         LocalTime openingTime = LocalTime.parse("10:30:00");
         LocalTime closingTime = LocalTime.parse("23:31:00");
         restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
         restaurant.addToMenu("Sweet corn soup",119);
         restaurant.addToMenu("Vegetable lasagne", 269);

     }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        // create instance of clock class
        Instant instant = Instant.parse("2021-04-04T12:40:42.00Z");

        // create ZoneId object for Asia/Calcutta zone
        ZoneId zoneId = ZoneId.of("UTC");

        // call fixed method
        Clock clock = Clock.fixed(instant, zoneId);

        // print details of clock
        System.out.println(clock.instant());
        Restaurant time= Mockito.mock(Restaurant.class);
        Mockito.when(time.getCurrentTime()).thenReturn(LocalTime.now(clock));
        System.out.println(time.getCurrentTime());
        assertEquals(true, restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
         // create instance of clock class
        Instant instant = Instant.parse("2021-04-04T09:09:42.00Z");

        // create ZoneId object for Asia/Calcutta zone
        ZoneId zoneId = ZoneId.of("UTC");

        // call fixed method
        Clock clock = Clock.fixed(instant, zoneId);

        // print details of clock
        System.out.println(clock.toString());
        restaurant=Mockito.mock(Restaurant.class);
         Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.now(clock));
        assertEquals(false, restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void selected_item_from_menu_passing_list_of_OrderItems()  {

        List<String> orderItems=new ArrayList<>();
        orderItems.add("Sweet corn soup");
        orderItems.add("Vegetable lasagne");
        assertEquals(null,restaurant.findCostSelectedItem(orderItems));
    }


    @Test
    public void selected_item_from_menu_returning_cost_in_positive()  {

        List<String> orderItems=new ArrayList<>();
        orderItems.add("Sweet corn soup");
        orderItems.add("Vegetable lasagne");
        assertEquals(388.0,restaurant.findCostSelectedItem(orderItems));
    }
}