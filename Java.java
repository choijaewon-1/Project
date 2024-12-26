/**
 * LunchMenu
 * 
 * �л����� ���� ���� �޴� ��õ ���α׷��Դϴ�. �� ���α׷��� �������� �Ĵ��� ��õ���ִ� 
 * �귿 ���, �Ĵ纰 ���� ���� �ý���, ���並 ���Ͽ� ���� �� �ҷ����� ����� �����մϴ�.
 * 
 * �ֿ� ���:
 * - ���� ���� �޴� ��õ (�귿).
 * - Ư�� �Ĵ翡 ���� ���� �߰�.
 * - ���並 ���Ͽ� ���� �� �ε�.
 * - ������ �Ĵ��� ���� ǥ��.
 * 
 * ����:
 * - ���α׷� ���� �� GUI �������̽��� ���� ����� ����մϴ�.
 * - "�귿 ������" ��ư�� ���� ���� �Ĵ� ��õ�� �޽��ϴ�.
 * - �Ĵ� ���並 �߰� �� Ȯ���մϴ�.
 * 
 * �ۼ���: �����
 * �ۼ���: 2024-12-26
 */
public class LunchMenu {

    // �Ĵ�� ��ǥ �޴��� ������ ������
    private static HashMap<String, String> restaurantMenuMap = new HashMap<>();

    // �Ĵ�� ���� ����Ʈ�� ������ ������
    private static HashMap<String, ArrayList<String>> restaurantReviews = new HashMap<>();

    // ���並 ������ ���� �̸�
    private static final String REVIEW_FILE = "reviews.txt";

    /**
     * ���α׷��� ���� �޼����Դϴ�.
     * 
     * - �Ĵ� �� �޴� �����͸� �ʱ�ȭ�մϴ�.
     * - ���� ���� ������ �ҷ��ɴϴ�.
     * - GUI �������̽��� �����ϰ� ���α׷��� �����մϴ�.
     * 
     */
    public static void main(String[] args) {
        // �Ĵ� �� ��ǥ �޴� �ʱ�ȭ
        restaurantMenuMap.put("�Ĵ� 1", "��ǥ �޴� 1");
        restaurantMenuMap.put("�Ĵ� 2", "��ǥ �޴� 2");
        restaurantMenuMap.put("�Ĵ� 3", "��ǥ �޴� 3");
        restaurantMenuMap.put("�Ĵ� 4", "��ǥ �޴� 4");
        restaurantMenuMap.put("�Ĵ� 5", "��ǥ �޴� 5");
        restaurantMenuMap.put("�Ĵ� 6", "��ǥ �޴� 6");
        restaurantMenuMap.put("�Ĵ� 7", "��ǥ �޴� 7");
        restaurantMenuMap.put("�Ĵ� 8", "��ǥ �޴� 8");

        // �� �Ĵ翡 ���� �� ���� ����Ʈ �ʱ�ȭ
        for (String restaurant : restaurantMenuMap.keySet()) {
            restaurantReviews.put(restaurant, new ArrayList<>());
        }

        // ���Ͽ��� ���� ���� �ҷ�����
        ReviewsFromFile();

        // GUI �ʱ�ȭ �� �ֿ� ��� ����
        // ...
    }

    /**
     * ���並 ���Ͽ� �����մϴ�.
     * 
     * - restaurantReviews ���� �����͸� ��ȸ�ϸ�, �� �Ĵ��� ���並 ���Ͽ� �ۼ��մϴ�.
     * - �� �Ĵ��� ����� "-"�� ���е˴ϴ�.
     */
    private static void saveReviewsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REVIEW_FILE))) {
            for (String restaurant : restaurantReviews.keySet()) {
                writer.write(restaurant + "\n");
                for (String review : restaurantReviews.get(restaurant)) {
                    writer.write(review + "\n");
                }
                writer.write("-\n"); // �Ĵ� ���м�
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "���� ���� �� ������ �߻��߽��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * ���並 ���Ͽ��� �ҷ��ɴϴ�.
     * 
     * - REVIEW_FILE���� �����͸� �о� restaurantReviews �ʿ� �����մϴ�.
     * - ���� ����:
     *   1. �Ĵ� �̸�
     *   2. ����� (�� �ٿ� �ϳ���)
     *   3. "-" (�Ĵ�� ���� ������)
     */
    private static void ReviewsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REVIEW_FILE))) {
            String line;
            String currentRestaurant = null;

            while ((line = reader.readLine()) != null) {
                if (restaurantMenuMap.containsKey(line)) {
                    currentRestaurant = line; // ���� �Ĵ� ����
                } else if ("-".equals(line)) {
                    currentRestaurant = null; // ���� �Ĵ� ���� ����
                } else if (currentRestaurant != null) {
                    restaurantReviews.get(currentRestaurant).add(line); // ���� �߰�
                }
            }
        } catch (FileNotFoundException e) {
            // ������ ������ ���� (�ʱ� ���� �� ���� ����)
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "���� �ε� �� ������ �߻��߽��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
        }
    }
}
