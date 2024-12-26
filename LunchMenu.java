import java.awt.BorderLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class LunchMenu{

    private static HashMap<String, String> restaurantMenuMap = new HashMap<>();
    private static HashMap<String, ArrayList<String>> restaurantReviews = new HashMap<>();
    private static final String REVIEW_FILE = "reviews.txt";
    
    public static void main(String[] args) {
        restaurantMenuMap.put("�Ĵ� 1", "��ǥ �޴� 1");
        restaurantMenuMap.put("�Ĵ� 2", "��ǥ �޴� 2");
        restaurantMenuMap.put("�Ĵ� 3", "��ǥ �޴� 3");
        restaurantMenuMap.put("�Ĵ� 4", "��ǥ �޴� 4");
        restaurantMenuMap.put("�Ĵ� 5", "��ǥ �޴� 5");
        restaurantMenuMap.put("�Ĵ� 6", "��ǥ �޴� 6");
        restaurantMenuMap.put("�Ĵ� 7", "��ǥ �޴� 7");
        restaurantMenuMap.put("�Ĵ� 8", "��ǥ �޴� 8");

        for (String restaurant : restaurantMenuMap.keySet()) {
            restaurantReviews.put(restaurant, new ArrayList<>());
        }

        ReviewsFromFile();
       
        JFrame frame = new JFrame("���� �޴� ��õ ���α׷�");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("���� �޴� ��õ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("����", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        JTextArea reviewArea = new JTextArea();
        reviewArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reviewArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton rouletteButton = new JButton("�귿 ������");
        JButton addReviewButton = new JButton("���� �߰��ϱ�");
        JButton saveButton = new JButton("���� ����");
        JButton exitButton = new JButton("����");
        buttonPanel.add(rouletteButton);
        buttonPanel.add(addReviewButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        rouletteButton.addActionListener(e -> {
            Random random = new Random();
            String[] restaurants = restaurantMenuMap.keySet().toArray(new String[0]);
            String selectedRestaurant = restaurants[random.nextInt(restaurants.length)];

            String result = "������ ��õ: " + selectedRestaurant + "! (��ǥ �޴�: " + restaurantMenuMap.get(selectedRestaurant) + ")";
            JOptionPane.showMessageDialog(frame, result, "�귿 ���", JOptionPane.INFORMATION_MESSAGE);

            reviewArea.setText(selectedRestaurant + "�� ���� ����:\n");
            ArrayList<String> selectedReviews = restaurantReviews.get(selectedRestaurant);
            if (selectedReviews.isEmpty()) {
                reviewArea.append("���� ���䰡 �����ϴ�.\n");
            } else {
                for (String review : selectedReviews) {
                    reviewArea.append("- " + review + "\n");
                }
            }
        });

        addReviewButton.addActionListener(e -> {
            String selectedRestaurant = (String) JOptionPane.showInputDialog(
                    frame,
                    "���並 �߰��� �Ĵ��� �����ϼ���:",
                    "���� �߰�",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    restaurantMenuMap.keySet().toArray(),
                    null
            );

            if (selectedRestaurant != null) {
                String review = JOptionPane.showInputDialog(frame, selectedRestaurant + "�� ���� ���並 �Է��ϼ���:");
                if (review != null && !review.trim().isEmpty()) {
                    restaurantReviews.get(selectedRestaurant).add(review);
                    JOptionPane.showMessageDialog(frame, "���䰡 �߰��Ǿ����ϴ�!", "����", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        saveButton.addActionListener(e -> {
            saveReviewsToFile();
            JOptionPane.showMessageDialog(frame, "���䰡 ���Ͽ� ����Ǿ����ϴ�!", "���� ����", JOptionPane.INFORMATION_MESSAGE);
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    // BufferedWriter�� FileWriter�� ����Ͽ� ���� �����͸� reviews.txt ���Ͽ� ����
    // �� �Ĵ��� ���並 ���Ͽ� ����ϰ�, -�� ���� �����ڸ� �ۼ���
    
    private static void saveReviewsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REVIEW_FILE))) {
            for (String restaurant : restaurantReviews.keySet()) {
                writer.write(restaurant + "\n");
                for (String review : restaurantReviews.get(restaurant)) {
                    writer.write(review + "\n");
                }
                writer.write("-\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "���� ���� �� ������ �߻��߽��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void ReviewsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REVIEW_FILE))) {
            String line;
            String currentRestaurant = null;

            while ((line = reader.readLine()) != null) {
                if (restaurantMenuMap.containsKey(line)) {
                    currentRestaurant = line;
                } else if ("-".equals(line)) {
                    currentRestaurant = null;
                } else if (currentRestaurant != null) {
                    restaurantReviews.get(currentRestaurant).add(line);
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "���� �ε� �� ������ �߻��߽��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
        }
    }
}
