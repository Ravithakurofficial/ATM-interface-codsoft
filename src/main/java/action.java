import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/action")
public class action extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public action() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String action1 = request.getParameter("action");

        try {
            int action = Integer.parseInt(action1);
            int accountBalance = 10000;

            switch (action) {
            case 1:
                String withdrawAmountStr = request.getParameter("withdrawAmount");
                try {
                    int withdrawAmount = Integer.parseInt(withdrawAmountStr);
                    if (withdrawAmount > 0 && withdrawAmount <= accountBalance) {
                        accountBalance -= withdrawAmount;
                        pw.println("Withdrawal successful. New Balance: Rs" + accountBalance);
                    } else {
                        pw.println("Withdrawal failed. Invalid amount or insufficient balance.");
                    }
                } catch (NumberFormatException e) {
                    pw.println("Invalid input. Please enter a valid number for withdrawal amount.");
                }
                break;

                case 2:
                    pw.println("Account Balance: Rs" + accountBalance);
                    break;
                case 3:
                    String depositAmountStr = request.getParameter("depositAmount");
                    try {
                        int depositAmount = Integer.parseInt(depositAmountStr);
                        if (depositAmount > 0) {
                            
                            
                            pw.println("Deposit successful. New Balance: Rs" + accountBalance);
                        } else {
                            pw.println("Deposit failed. Invalid amount.");
                        }
                    } catch (NumberFormatException e) {
                        pw.println("Invalid input. Please enter a valid number for deposit amount.");
                    }
                    break;
                case 4:
                    pw.println("Thank you for using our ATM. Goodbye!");
                    break;

                default:
                    pw.println("Invalid action");
            }
        } catch (NumberFormatException e) {
            pw.println("Invalid input. Please select a valid action.");
        }
    }
}
