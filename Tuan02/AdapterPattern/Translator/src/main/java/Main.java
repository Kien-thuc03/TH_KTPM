public class Main {
    public static void main(String[] args) {
        JapaneseAdaptee japaneseReceiver = new JapaneseAdaptee();

        TranslatorAdapter translator = new TranslatorAdapter(japaneseReceiver);

        VietnamesePerson vietnamesePerson = new VietnamesePerson(translator);

        vietnamesePerson.sendMessageToJapanese("Xin chào");
        vietnamesePerson.sendMessageToJapanese("Cảm ơn");
        vietnamesePerson.sendMessageToJapanese("Hẹn gặp lại");
    }
}