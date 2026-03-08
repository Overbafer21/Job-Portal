import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class JobApplication {
    String name;
    String email;
    String jobTitle;
    String qualification;

    JobApplication(String name, String email, String jobTitle, String qualification) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.qualification = qualification;
    }
}

public class JobPortalSystem extends JFrame {
    private final JTextField tfName;
    private final JTextField tfEmail;
    private final JTextField tfJobTitle;
    private final JTextField tfQualification;
    private final JTable table;
    private final DefaultTableModel model;
    private final List<JobApplication> applications = new ArrayList<>();

    public JobPortalSystem() {
        setTitle("🚀 Карьерный Портал");
        setSize(980, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));
        getContentPane().setBackground(new Color(244, 247, 255));

        JPanel header = new GradientHeader();
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(18, 22, 18, 22));

        JLabel heading = new JLabel("🚀 Карьерный Портал: подайте заявку за 1 минуту", JLabel.LEFT);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heading.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Уникальный дизайн: темная шапка + светлые карточки");
        subtitle.setForeground(new Color(215, 231, 255));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel titleBox = new JPanel(new GridLayout(2, 1));
        titleBox.setOpaque(false);
        titleBox.add(heading);
        titleBox.add(subtitle);
        header.add(titleBox, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel formCard = new JPanel(new GridBagLayout());
        formCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(219, 228, 250)),
                new EmptyBorder(18, 16, 18, 16)
        ));
        formCard.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 4, 8, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        tfName = buildField();
        tfEmail = buildField();
        tfJobTitle = buildField();
        tfQualification = buildField();

        addField(formCard, gbc, 0, "👤 Имя кандидата", tfName);
        addField(formCard, gbc, 1, "✉️ Email", tfEmail);
        addField(formCard, gbc, 2, "💼 Желаемая должность", tfJobTitle);
        addField(formCard, gbc, 3, "🎓 Квалификация", tfQualification);

        JPanel controls = new JPanel(new GridLayout(1, 2, 10, 0));
        controls.setOpaque(false);
        JButton btnApply = createButton("Добавить заявку", new Color(46, 125, 50));
        JButton btnClear = createButton("Очистить форму", new Color(211, 47, 47));
        controls.add(btnApply);
        controls.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formCard.add(controls, gbc);

        JPanel leftWrapper = new JPanel(new BorderLayout());
        leftWrapper.setOpaque(false);
        leftWrapper.setBorder(new EmptyBorder(0, 12, 12, 0));

        JLabel formTitle = new JLabel("Подача заявки");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        formTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        leftWrapper.add(formTitle, BorderLayout.NORTH);
        leftWrapper.add(formCard, BorderLayout.CENTER);
        add(leftWrapper, BorderLayout.WEST);

        model = new DefaultTableModel(new String[]{"Имя", "Email", "Должность", "Квалификация"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(63, 81, 181));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(224, 231, 255));
        table.setGridColor(new Color(236, 239, 249));

        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setBorder(BorderFactory.createLineBorder(new Color(219, 228, 250)));

        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.setOpaque(false);
        tableWrapper.setBorder(new EmptyBorder(0, 0, 12, 12));
        JLabel tableTitle = new JLabel("Список заявок");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        tableWrapper.add(tableTitle, BorderLayout.NORTH);
        tableWrapper.add(tablePane, BorderLayout.CENTER);

        add(tableWrapper, BorderLayout.CENTER);

        btnApply.addActionListener(e -> applyJob());
        btnClear.addActionListener(e -> clearForm());

        applications.add(new JobApplication("Иван Петров", "ivan@mail.ru", "Java-разработчик", "Бакалавр ИТ"));
        applications.add(new JobApplication("Мария Кузнецова", "maria@mail.ru", "UI/UX-дизайнер", "Дизайн интерфейсов"));
        updateTable();
    }

    private JTextField buildField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(208, 217, 243)),
                new EmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(jLabel, gbc);

        gbc.gridy = row + 1;
        panel.add(field, gbc);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBorder(new EmptyBorder(10, 12, 10, 12));
        return button;
    }

    private void applyJob() {
        String name = tfName.getText().trim();
        String email = tfEmail.getText().trim();
        String jobTitle = tfJobTitle.getText().trim();
        String qualification = tfQualification.getText().trim();

        if (name.isEmpty() || email.isEmpty() || jobTitle.isEmpty() || qualification.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, заполните все поля.", "Проверка данных", JOptionPane.WARNING_MESSAGE);
            return;
        }

        applications.add(new JobApplication(name, email, jobTitle, qualification));
        updateTable();
        clearForm();
    }

    private void updateTable() {
        model.setRowCount(0);
        for (JobApplication ja : applications) {
            model.addRow(new Object[]{ja.name, ja.email, ja.jobTitle, ja.qualification});
        }
    }

    private void clearForm() {
        tfName.setText("");
        tfEmail.setText("");
        tfJobTitle.setText("");
        tfQualification.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JobPortalSystem().setVisible(true));
    }
}

class GradientHeader extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, new Color(25, 42, 86), w, h, new Color(63, 81, 181));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
