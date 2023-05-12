import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProblemSolvingTask {

    public static void main(String[] args) {

        String input ="dd(df)a(ghhh)";

        System.out.printf("Input is : %s%n",input);

        ValidationResultDTO validationResultDTO  = checkIfInputIsValid(input);

        if (!validationResultDTO.valid){
            System.out.printf("Input Not Valid [ %s ]",validationResultDTO.message);
        }else {
            if (input.matches(".*[()]+.*")){
                reverseString(input);
            }else{
                System.out.printf("Output is : %s",input);
            }
        }
    }

    public static ValidationResultDTO checkIfInputIsValid(String input)
    {
        if (!input.matches("[a-z()]+")){
            return new ValidationResultDTO(false,"String only contains lower case English characters and parentheses.");
        }
        if(!(input.length() >= 1 && input.length() <= 2000))
        {
            return new ValidationResultDTO(false,"It is guaranteed that all parentheses are balanced.");
        }
        return new ValidationResultDTO(true,"");
    }


    public static void reverseString(String input){

        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(input);
        List<String> matches = new ArrayList<>();

        while (matcher.find()) {
            String match = matcher.group(1);
            matches.add(match);
        }

        String result = input;
        for (String match : matches) {
            result = result.replace("(" + match + ")", "(" +new StringBuilder(match).reverse()+ ")");
        }
        System.out.printf("Output is : %s",result);
    }

    public record ValidationResultDTO(boolean valid,String message) {}


}
