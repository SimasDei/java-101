package academy.learnprogramming;

import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Simple "Eliza" program.
 * <p>
 * The program appears to have a conversation with the user, using string replacement
 * and basic parsing, to give the appearance of understanding what the user types.
 * <p>
 * The responses are based on a Python program by Joe Strout, Jeff Epler and Jez Higgins.
 * <p>
 * Original available at https://github.com/jezhiggins/eliza.py
 * <p>
 * Licensed under the terms of the MIT License.
 */
public class Doctor {

    private static final Random RANDOM = new Random();

    private static final List<String> MATCHES = List.of(
            "life",
            "i need",
            "why don't", "why can't",
            "i can", "i am", "i'm",
            "are you",
            "what", "how",
            "because",
            "sorry",
            "i think",
            "friend",
            "yes",
            "computer",
            "is it", "it is",
            "can you", "can i",
            "you are", "you're",
            "i don't",
            "i feel", "i have", "i've",
            "i would",
            "is there",
            "my",
            "you",
            "why",
            "i want",
            "mother", "father", "child",
            "?",
            "hello", "hi", "hey",
            "quit"
    );

    private static final Map<String, String> REFLECTIONS = Map.ofEntries(
            Map.entry(" am", " are"),
            Map.entry(" was", " were"),
            Map.entry("I ", "you "),
            Map.entry("I'd", "you would"),
            Map.entry("I've", "you have"),
            Map.entry("I'll", "you will"),
            Map.entry("my ", "your "),
            Map.entry("are ", "am "),
            Map.entry("you've", "I have"),
            Map.entry("you'll", "I will"),
            Map.entry("your", "my"),
            Map.entry("yours", "mine"),
            Map.entry("you", "me"),
            Map.entry("me", "you"),
            Map.entry("I'm", "you're")
    );

    private static final List<List<String>> RESPONSES = List.of(
            List.of("Life? Don't talk to me about life.", "At least you have a life, I'm stuck inside this computer.", "Life can be good. Remember, 'this, too, will pass'."),
            List.of("Why do you need %1?", "Would it really help you to get %1?", "Are you sure you need %1?"),
            List.of("Do you really think I don't %1?", "Perhaps eventually I will %1.", "Do you really want me to %1?"),
            List.of("Do you think you should be able to %1?", "If you could %1, what would you do?", "I don't know -- why can't you %1?", "Have you really tried?"),
            List.of("How do you know you can't %1?", "Perhaps you could %1 if you tried.", "What would it take for you to %1?"),
            List.of("Did you come to me because you are %1?", "How long have you been %1?", "How do you feel about being %1?"),
            List.of("How does being %1 make you feel?", "Do you enjoy being %1?", "Why do you tell me you're %1?", "Why do you think you're %1?"),
            List.of("Why does it matter whether I am %1?", "Would you prefer it if I were not %1?", "Perhaps you believe I am %1.", "I may be %1 -- what do you think?"),
            List.of("Why do you ask?", "How would an answer to that help you?", "What do you think?"),
            List.of("How do you suppose?", "Perhaps you can answer your own question.", "What is it you're really asking?"),
            List.of("Is that the real reason?", "What other reasons come to mind?", "Does that reason apply to anything else?", "If %1, what else must be true?"),
            List.of("There are many times when no apology is needed.", "What feelings do you have when you apologize?"),
            List.of("Do you doubt %1?", "Do you really think so?", "But you're not sure %1?"),
            List.of("Tell me more about your friends.", "When you think of a friend, what comes to mind?", "Why don't you tell me about a childhood friend?"),
            List.of("You seem quite sure.", "OK, but can you elaborate a bit?"),
            List.of("Are you really talking about me?", "Does it seem strange to talk to a computer?", "How do computers make you feel?", "Do you feel threatened by computers?"),
            List.of("Do you think it is %1?", "Perhaps it is %1 -- what do you think?", "If it were %1, what would you do?", "It could well be that %1."),
            List.of("You seem very certain.", "If I told you that it probably isn't %1, what would you feel?"),
            List.of("What makes you think I can't %1?", "If I could %1, then what?", "Why do you ask if I can %1?"),
            List.of("Perhaps you don't want to %1.", "Do you want to be able to %1?", "If you could %1, would you?"),
            List.of("Why do you think I am %1?", "Does it please you to think that I'm %1?", "Perhaps you would like me to be %1.", "Perhaps you're really talking about yourself?"),
            List.of("Why do you say I am %1?", "Why do you think I am %1?", "Are we talking about you, or me?"),
            List.of("Don't you really %1?", "Why don't you %1?", "Do you want to %1?"),
            List.of("Good, tell me more about these feelings.", "Do you often feel %1?", "When do you usually feel %1?", "When you feel %1, what do you do?"),
            List.of("Why do you tell me that you've %1?", "Have you really %1?", "Now that you have %1, what will you do next?"),
            List.of("Why do you tell me that you've %1?", "Have you really %1?", "Now that you have %1, what will you do next?"),
            List.of("Could you explain why you would %1?", "Why would you %1?", "Who else knows that you would %1?"),
            List.of("Do you think there is %1?", "It's likely that there is %1.", "Would you like there to be %1?"),
            List.of("I see, your %1.", "Why do you say that your %1?", "When you're %1, how do you feel?"),
            List.of("We should be discussing you, not me.", "Why do you say that about me?", "Why do you care whether I %1?"),
            List.of("Why don't you tell me the reason why %1?", "Why do you think %1?"),
            List.of("What would it mean to you if you got %1?", "Why do you want %1?", "What would you do if you got %1?", "If you got %1, then what would you do?"),
            List.of("Tell me more about your mother.", "What was your relationship with your mother like?", "How do you feel about your mother?", "How does this relate to your feelings today?", "Good family relations are important."),
            List.of("Tell me more about your father.", "How did your father make you feel?", "How do you feel about your father?", "Does your relationship with your father relate to your feelings today?", "Do you have trouble showing affection with your family?"),
            List.of("Did you have close friends as a child?", "What is your favorite childhood memory?", "Do you remember any dreams or nightmares from childhood?", "Did the other children sometimes tease you?", "How do you think your childhood experiences relate to your feelings today?"),
            List.of("Why do you ask that?", "Please consider whether you can answer your own question.", "Perhaps the answer lies within yourself?", "Why don't you tell me?"),
            List.of("Hello... I'm glad you could drop by today.", "Hello there... how are you today?", "Hello, how are you feeling today?"),
            List.of("Hi... I'm glad you could drop by today.", "Hi there... how are you today?", "Hi, how are you feeling today?"),
            List.of("Hey... I'm glad you could drop by today.", "Hey there... how are you today?", "Hey, how are you feeling today?"),
            List.of("Thank you for talking with me.", "Good-bye.", "Thank you, that will be $150.  Have a good day!"),
            List.of("Please tell me more.", "Let's change focus a bit... Tell me about your family.", "Can you elaborate on that?", "Why do you say that %1?", "I see.", "Very interesting.", "%1?", "I see.  And what does that tell you?", "How does that make you feel?", "How do you feel when you say that?")
    );


    public static String intro() {
        return String.join(System.lineSeparator(),
                "I'm Eliza",
                "---------",
                "Talk to the program by typing in plain English, using normal upper-",
                "and lower-case letters and punctuation.  Enter 'quit' when done.",
                System.lineSeparator(),
                "Hello. How are you feeling today?");
    }

    public static String response(String UserInput) {
        // check through the matches list, and if there's a match, strip off the match and replace with the response.
        //
        // If the response contains %1, replace that with the Remainder of the input string.
        // Before replacing, change words in the Remainder of the input with the corresponding entry from the reflections dictionary.
        var output = "";
        String Remainder = "";

        for (var index = 0; index < MATCHES.size(); index++) {
            String match = MATCHES.get(index);
            int position = UserInput.toLowerCase().indexOf(match);

            if (position > -1) {
                // found a match, delete everything up to the end of the text we found.
                var rem = UserInput.substring(0, position + match.length()); // get String that need to be removed
                rem = UserInput.replace(rem, "");   // replace removed String with empty e.g. remove it

                // Now replace the reflections: I -> you, etc
                // We need to split the input into words, to avoid changing eg. me -> you then the same you -> me.
                String[] words = rem.split(" ");

                for (int i = 0; i < words.length; i++) {
                    for (String reflection : REFLECTIONS.keySet()) {
                        if (words[i].equals(reflection)) {
                            words[i] = REFLECTIONS.get(reflection);
                        }
                    }
                }

                // now join the words back up again
                rem = String.join(" ", words);

                // strip leading and trailing spaces
                Remainder = rem.trim();

                var randomIndex = RANDOM.nextInt(RESPONSES.get(index).size());
                output = RESPONSES.get(index).get(randomIndex);
                break;
            }
        }

        // If there wasn't a match, use the last item in the responses list.
        if (output.isBlank()) {
            int randomIndex = RANDOM.nextInt(RESPONSES.get(RESPONSES.size() - 1).size());
            output = RESPONSES.get(RESPONSES.size() - 1).get(randomIndex);
        }

        // Now substitute the modified input for %1 (if it exists) in the response.
        output = output.replace("%1", Remainder);
        return output;
    }

}
