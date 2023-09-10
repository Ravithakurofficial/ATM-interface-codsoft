import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/processWithdrawal")
public class processWithdrawal extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public processWithdrawal() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        // Get the values from the form
        String withdrawAmountStr = request.getParameter("withdrawAmount");
        String currentAmountStr = request.getParameter("CurrentAmount");

        try {
            // Parse the values as integers
            int withdrawAmount = Integer.parseInt(withdrawAmountStr);
            int currentAmount = Integer.parseInt(currentAmountStr);

            // Check if the withdrawal amount is valid
            if (withdrawAmount > 0 && withdrawAmount <= currentAmount) {
                // Perform the withdrawal
                int newBalance = currentAmount - withdrawAmount;
                pw.print("Withdrawal successful. New Balance: $" + newBalance);
            } else {
                pw.print("Withdrawal failed. Invalid amount or insufficient balance.");
            }
        } catch (NumberFormatException e) {
            pw.print("Invalid input. Please enter a valid number.");
        } finally {
            pw.close(); // Close the PrintWriter
        }
    }
}
