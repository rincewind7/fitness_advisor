import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sportsman extends Person {
    private String goal;
    public List<String> weeklyDietDescriptions = new ArrayList<>();
    public List<String> weeklyTrainingsDescriptions = new ArrayList<>();
    private List<Training> trainings = new ArrayList<>(); //do wstrzykiwania zależności
    private List<Meal> meals = new ArrayList<>(); // do wstrzykiwania zależności


    public Sportsman(String goal, String name, String lastName) {
        super(name, lastName);
        this.goal = goal;
    }


    public void fillWeeklyData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj opis diety na ten tydzień:");
        String dietDescription = scanner.nextLine();
        this.weeklyDietDescriptions.add(dietDescription);

        System.out.println("Podaj opis treningów na ten tydzień:");
        String trainingDescription = scanner.nextLine();
        this.weeklyTrainingsDescriptions.add(trainingDescription);
        System.out.println("Dane zostały dodane.");
    }

    public void fillWeeklyData(String dieta, String treningi) {
        this.weeklyDietDescriptions.add(dieta);
        this.weeklyTrainingsDescriptions.add(treningi);
    }

    //generowanie porad na 3 różne sposoby
    public void generateAdviceBasedOnLastWeeklyData(GPT4Connector connector) {
        String jsonString = connector.sendRequest(this.generateWeeklyRequestText());
        String result = JsonParser.parseContentFromJson(jsonString);
        System.out.println(result);
    }

    public  void generateAdviceBasedOnGoal(GPT4Connector connector) {
        String jsonString = connector.sendRequest(this.generateBasicRequestText());  //wstawia konkretny json zapytania
        String result = JsonParser.parseContentFromJson(jsonString);
        System.out.println(result);
    }

    public void generateAdviseBasenOnMeal(Meal meal, GPT4Connector connector) {
        String jsonString = connector.sendRequest(this.generateMealRequestText(meal));
        String result = JsonParser.parseContentFromJson(jsonString);
        System.out.println(result);
    }

    //przykład wstrzykiwania zależności
    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    //generowanie zawartości requestów na różne sposoby
    public String generateBasicRequestText() {
        return "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \"Doradź mi w kwestii żywienia i treningów. "
                + "Cel treningowy: " + goal
                + "\"}]"
                + "}";
    }

    public String generateWeeklyRequestText() {
        return "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \"Doradź mi w kwestii żywienia i treningów. Podpowiedz co aktualnie robię nie tak. "
                + "Cel treningowy: " + goal
                + "Opis mojego odżywiania w tym tygodniu: " + weeklyDietDescriptions.get(weeklyDietDescriptions.size() - 1)
                + "Opis moich treningów w tym tygodniu: " + weeklyTrainingsDescriptions.get(weeklyTrainingsDescriptions.size() - 1)
                + "\"}]"
                + "}";
    }
    public String generateMealRequestText(Meal meal) {
        return "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": [{\"role\": \"user\", \"content\": \"Doradź mi w kwestii żywienia i treningów. Podpowiedz co aktualnie robię nie tak. "
                + "Mój cel: " + goal
                + "Dzisiaj jako: " + meal.getType()
                + " Zjadłam: " + meal.getDescription()
                + "Kaloryczność: " + meal.getCalories()
                + "Oceń mój posiłek i zaplanuj dla mnie pozostałe posiłki (opisz ich kaloryczność) uwzględniając mój cel"
                + "\"}]"
                + "}";
    }

}



