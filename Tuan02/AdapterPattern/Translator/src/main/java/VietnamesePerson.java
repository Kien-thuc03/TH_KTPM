public class VietnamesePerson {
    private VietnameseTarget translator;

    public VietnamesePerson(VietnameseTarget translator) {
        this.translator = translator;
    }

    public void sendMessageToJapanese(String message) {
        System.out.println("Người Việt gửi tin nhắn: " + message);
        translator.sendMessage(message);
    }
}