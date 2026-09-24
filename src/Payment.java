public class Payment {

    private String paymentId;
    private double amount;
    private PaymentStatus status;

    public Payment(String paymentId, double amount) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = null;
    }

    public boolean processPayment(boolean shouldSucceed) {

        if (shouldSucceed) {
            status = PaymentStatus.SUCCESS;
            return true;
        } else {
            status = PaymentStatus.FAILED;
            return false;
        }
    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}