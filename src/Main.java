
public class Main {
    public static void main(String[] args) {

        //Tworzenie potrzebnych obiektów
        String apiKey = GPT4Connector.loadApiKeyFromFile("api_key.txt");
        GPT4Connector connector = new GPT4Connector("https://api.openai.com", apiKey);
        Sportsman kgumkowska = new Sportsman("Jako starszy człowiek chcę zadbać o swoje zdrowie", "Kamila", "Gumkowska");

        //WYWOŁANIA METOD
        //test1
        kgumkowska.generateAdviceBasedOnGoal(connector);
//
//        //test2
//        kgumkowska.fillWeeklyData("Codziennie na obiad kotles schabowy, na śniadanie drożdżówka, na kolację gyros", "Byłam 2 razy na spacerze po 15 minut");
//        kgumkowska.generateAdviceBasedOnLastWeeklyData(connector);
//
//        //test3
//        kgumkowska.fillWeeklyData();
//        kgumkowska.generateAdviceBasedOnLastWeeklyData(connector);

        //test4
//        Meal meal1 = new Meal(MealType.SNIADANIE, 900, "Słodka chałka z sosem śmietanowym i bekonem");
//        kgumkowska.generateAdviseBasenOnMeal(meal1, connector);

        }
    }


