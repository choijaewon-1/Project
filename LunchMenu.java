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
        restaurantMenuMap.put("식당 1", "대표 메뉴 1");
        restaurantMenuMap.put("식당 2", "대표 메뉴 2");
        restaurantMenuMap.put("식당 3", "대표 메뉴 3");
        restaurantMenuMap.put("식당 4", "대표 메뉴 4");
        restaurantMenuMap.put("식당 5", "대표 메뉴 5");
        restaurantMenuMap.put("식당 6", "대표 메뉴 6");
        restaurantMenuMap.put("식당 7", "대표 메뉴 7");
        restaurantMenuMap.put("식당 8", "대표 메뉴 8");

        for (String restaurant : restaurantMenuMap.keySet()) {
            restaurantReviews.put(restaurant, new ArrayList<>());
        }

        ReviewsFromFile();
       
        JFrame frame = new JFrame("점심 메뉴 추천 프로그램");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("점심 메뉴 추천", SwingConstants.CENTER);
        titleLabel.setFont(new Font("굴림", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        JTextArea reviewArea = new JTextArea();
        reviewArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reviewArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton rouletteButton = new JButton("룰렛 돌리기");
        JButton addReviewButton = new JButton("리뷰 추가하기");
        JButton saveButton = new JButton("리뷰 저장");
        JButton exitButton = new JButton("종료");
        buttonPanel.add(rouletteButton);
        buttonPanel.add(addReviewButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        rouletteButton.addActionListener(e -> {
            Random random = new Random();
            String[] restaurants = restaurantMenuMap.keySet().toArray(new String[0]);
            String selectedRestaurant = restaurants[random.nextInt(restaurants.length)];

            String result = "오늘의 추천: " + selectedRestaurant + "! (대표 메뉴: " + restaurantMenuMap.get(selectedRestaurant) + ")";
            JOptionPane.showMessageDialog(frame, result, "룰렛 결과", JOptionPane.INFORMATION_MESSAGE);

            reviewArea.setText(selectedRestaurant + "에 대한 리뷰:\n");
            ArrayList<String> selectedReviews = restaurantReviews.get(selectedRestaurant);
            if (selectedReviews.isEmpty()) {
                reviewArea.append("아직 리뷰가 없습니다.\n");
            } else {
                for (String review : selectedReviews) {
                    reviewArea.append("- " + review + "\n");
                }
            }
        });

        addReviewButton.addActionListener(e -> {
            String selectedRestaurant = (String) JOptionPane.showInputDialog(
                    frame,
                    "리뷰를 추가할 식당을 선택하세요:",
                    "리뷰 추가",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    restaurantMenuMap.keySet().toArray(),
                    null
            );

            if (selectedRestaurant != null) {
                String review = JOptionPane.showInputDialog(frame, selectedRestaurant + "에 대한 리뷰를 입력하세요:");
                if (review != null && !review.trim().isEmpty()) {
                    restaurantReviews.get(selectedRestaurant).add(review);
                    JOptionPane.showMessageDialog(frame, "리뷰가 추가되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        saveButton.addActionListener(e -> {
            saveReviewsToFile();
            JOptionPane.showMessageDialog(frame, "리뷰가 파일에 저장되었습니다!", "저장 성공", JOptionPane.INFORMATION_MESSAGE);
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    // BufferedWriter와 FileWriter를 사용하여 리뷰 데이터를 reviews.txt 파일에 저장
    // 각 식당의 리뷰를 파일에 기록하고, -로 리뷰 구분자를 작성함
    
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
            JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "파일 로드 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
