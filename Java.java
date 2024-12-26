/**
 * LunchMenu
 * 
 * 학생들을 위한 점심 메뉴 추천 프로그램입니다. 이 프로그램은 랜덤으로 식당을 추천해주는 
 * 룰렛 기능, 식당별 리뷰 관리 시스템, 리뷰를 파일에 저장 및 불러오는 기능을 포함합니다.
 * 
 * 주요 기능:
 * - 랜덤 점심 메뉴 추천 (룰렛).
 * - 특정 식당에 대한 리뷰 추가.
 * - 리뷰를 파일에 저장 및 로드.
 * - 선택한 식당의 리뷰 표시.
 * 
 * 사용법:
 * - 프로그램 실행 후 GUI 인터페이스를 통해 기능을 사용합니다.
 * - "룰렛 돌리기" 버튼을 눌러 랜덤 식당 추천을 받습니다.
 * - 식당 리뷰를 추가 및 확인합니다.
 * 
 * 작성자: 최재원
 * 작성일: 2024-12-26
 */
public class LunchMenu {

    // 식당과 대표 메뉴를 매핑한 데이터
    private static HashMap<String, String> restaurantMenuMap = new HashMap<>();

    // 식당과 리뷰 리스트를 매핑한 데이터
    private static HashMap<String, ArrayList<String>> restaurantReviews = new HashMap<>();

    // 리뷰를 저장할 파일 이름
    private static final String REVIEW_FILE = "reviews.txt";

    /**
     * 프로그램의 메인 메서드입니다.
     * 
     * - 식당 및 메뉴 데이터를 초기화합니다.
     * - 기존 리뷰 파일을 불러옵니다.
     * - GUI 인터페이스를 설정하고 프로그램을 실행합니다.
     * 
     */
    public static void main(String[] args) {
        // 식당 및 대표 메뉴 초기화
        restaurantMenuMap.put("식당 1", "대표 메뉴 1");
        restaurantMenuMap.put("식당 2", "대표 메뉴 2");
        restaurantMenuMap.put("식당 3", "대표 메뉴 3");
        restaurantMenuMap.put("식당 4", "대표 메뉴 4");
        restaurantMenuMap.put("식당 5", "대표 메뉴 5");
        restaurantMenuMap.put("식당 6", "대표 메뉴 6");
        restaurantMenuMap.put("식당 7", "대표 메뉴 7");
        restaurantMenuMap.put("식당 8", "대표 메뉴 8");

        // 각 식당에 대해 빈 리뷰 리스트 초기화
        for (String restaurant : restaurantMenuMap.keySet()) {
            restaurantReviews.put(restaurant, new ArrayList<>());
        }

        // 파일에서 기존 리뷰 불러오기
        ReviewsFromFile();

        // GUI 초기화 및 주요 기능 구현
        // ...
    }

    /**
     * 리뷰를 파일에 저장합니다.
     * 
     * - restaurantReviews 맵의 데이터를 순회하며, 각 식당의 리뷰를 파일에 작성합니다.
     * - 각 식당의 리뷰는 "-"로 구분됩니다.
     */
    private static void saveReviewsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REVIEW_FILE))) {
            for (String restaurant : restaurantReviews.keySet()) {
                writer.write(restaurant + "\n");
                for (String review : restaurantReviews.get(restaurant)) {
                    writer.write(review + "\n");
                }
                writer.write("-\n"); // 식당 구분선
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 리뷰를 파일에서 불러옵니다.
     * 
     * - REVIEW_FILE에서 데이터를 읽어 restaurantReviews 맵에 저장합니다.
     * - 파일 포맷:
     *   1. 식당 이름
     *   2. 리뷰들 (각 줄에 하나씩)
     *   3. "-" (식당과 리뷰 구분자)
     */
    private static void ReviewsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REVIEW_FILE))) {
            String line;
            String currentRestaurant = null;

            while ((line = reader.readLine()) != null) {
                if (restaurantMenuMap.containsKey(line)) {
                    currentRestaurant = line; // 현재 식당 설정
                } else if ("-".equals(line)) {
                    currentRestaurant = null; // 현재 식당 리뷰 종료
                } else if (currentRestaurant != null) {
                    restaurantReviews.get(currentRestaurant).add(line); // 리뷰 추가
                }
            }
        } catch (FileNotFoundException e) {
            // 파일이 없으면 무시 (초기 실행 시 정상 동작)
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "파일 로드 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
