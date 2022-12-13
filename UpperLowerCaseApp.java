package gr.aueb.cf.projects;

public class UpperLowerCaseApp {

    public static void main(String[] args) {
        String sentence = "Athanassios";
        StringBuilder sb = new StringBuilder();
        String s;

        int i = 0;
        for (char ch : sentence.toCharArray()) {
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(ch));
            } else {
                sb.append(Character.toLowerCase(ch));
            }
            i++;
        }

        System.out.println(sb);
    }
}
