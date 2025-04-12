public class TranslatorAdapter implements VietnameseTarget {
    private JapaneseAdaptee japaneseReceiver;

    public TranslatorAdapter(JapaneseAdaptee japaneseReceiver) {
        this.japaneseReceiver = japaneseReceiver;
    }

    @Override
    public void sendMessage(String vietnameseMessage) {
        String japaneseMessage = translateToJapanese(vietnameseMessage);
        japaneseReceiver.receiveJapaneseMessage(japaneseMessage);
    }

    private String translateToJapanese(String vietnameseMessage) {
        if (vietnameseMessage.equals("Xin chào")) {
            return "こんにちは";
        } else if (vietnameseMessage.equals("Cảm ơn")) {
            return "ありがとう";
        }
        return "の日本語訳)";
    }
}