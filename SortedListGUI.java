import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for inserting and searching strings in a sorted list.
 */
public class SortedListGUI extends JFrame {
    private SortedList sortedList;
    private JTextArea display;
    private JTextField inputField;
    private JTextField searchField;

    /** Sets up the entire GUI layout and event listeners. */
    public SortedListGUI() {
        sortedList = new SortedList();

        setTitle("Sorted List GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for insertion
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(15);
        JButton insertBtn = new JButton("Insert");
        inputPanel.add(new JLabel("Add:"));
        inputPanel.add(inputField);
        inputPanel.add(insertBtn);
        add(inputPanel, BorderLayout.NORTH);

        // Center area for displaying list
        display = new JTextArea();
        display.setEditable(false);
        add(new JScrollPane(display), BorderLayout.CENTER);

        // Bottom panel for search
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(15);
        JButton searchBtn = new JButton("Search");
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        add(searchPanel, BorderLayout.SOUTH);

        // Button actions
        insertBtn.addActionListener(e -> {
            String value = inputField.getText().trim();
            if (!value.isEmpty()) {
                sortedList.insert(value);
                updateDisplay();
                inputField.setText("");
            }
        });

        searchBtn.addActionListener(e -> {
            String value = searchField.getText().trim();
            if (!value.isEmpty()) {
                int index = sortedList.search(value);
                if (index != -1) {
                    display.append("\nFound \"" + value + "\" at index " + index + ".");
                } else {
                    int insertionPoint = getInsertionPoint(value);
                    display.append("\n\"" + value + "\" not found. Would go at index " + insertionPoint + ".");
                }
                searchField.setText("");
            }
        });

        setVisible(true);
    }

    /** Updates the text area with the current state of the list. */
    private void updateDisplay() {
        display.setText("Current List:\n" + sortedList.getList());
    }

    /**
     * Gets where the value would be inserted using same logic as insert().
     */
    private int getInsertionPoint(String value) {
        int low = 0, high = sortedList.getList().size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (sortedList.getList().get(mid).compareTo(value) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    /** Launches the GUI application. */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortedListGUI::new);
    }
}
