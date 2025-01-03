2020011894 최재원
1. 개요

점심 메뉴 추천을 위한 랜덤 룰렛 기능
각 식당별 리뷰 추가 및 저장 기능
리뷰 데이터의 파일 입출력을 통한 지속성 제공
GUI를 통해 사용자가 간편하게 프로그램을 사용할 수 있도록 인터페이스 제공

1.1 목적 : 청주대 학생들의 점심 메뉴 고민을 해결하고 재미와 편리함을 제공하는 프로그램 입니다.

1.2 대상 : 청주대 학생들

2. 프로그램의 중요성 및 필요성

2.1 중요성 : 이 프로그램은 학생들이 메뉴 선택에 소요되는 시간을 절약하고 재미있는 메뉴 선택을 하도록 돕습니다.

2.2 필요성 : 학생들이 먹고싶은 메뉴가 많거나 없을 때 혹은 학생그룹에서 먹고싶은 메뉴가 서로 다를 때 선택이 용이하게 돕습니다.

3. 프로그램 수행 절차

이 프로그램은 랜덤 룰렛 기능을 통해 사용자가 점심 메뉴로 갈 식당과 그 식당의 대표 메뉴를 추천받을 수 있도록 설계되었습니다.
사용자는 추천받은 식당에 대한 리뷰를 추가가능하고, 저장된 리뷰를 파일을 불러올 수 있습니다.
프로그램은 GUI를 통해 사용자와 상호작용하며, 각 식당에 대한 리뷰를 텍스트 영역에 표시합니다.

설계
룰렛 기능 : 랜덤으로 식당을 선택하여 추천 메뉴를 표시하고 Java의 Random 클래스를 사용한다
리뷰 관리 기능 : 사용자 입력을 통해 리뷰를 특정 식당에 추가하고 리뷰 데이터를 GUI와 연동하여 출력한다
파일 입출력 : 리뷰 데이터를 파일로 저장(BufferedWriter) 및 불러오기(BufferedReader)를 한다
GUI 설계 : JFrame과 JPanel을 활용하여 레이아웃 구성한다
데이터 초기화 : 식당 및 대표 메뉴 데이터를 HashMap으로 정의하고 각 식당에 대한 빈 리뷰 리스트 초기화한다.
룰렛 기능 구현 : Random 클래스를 이용해 식당을 랜덤으로 선택하고 GUI 팝업(JOptionPane)으로 결과 표시한다.
리뷰 추가 기능 구현 : 사용자로부터 리뷰를 입력받아 HashMap에 저장한다.
파일 입출력 구현 : 리뷰 데이터를 reviews.txt로 저장/불러오는 기능 추가한다.
GUI 설계 및 연동 : JFrame과 JPanel로 화면 구성하고 각 버튼에 이벤트 리스너 추가하여 기능과 연동한다

5. 느낌점
이번 프로그램을 설계하면서 설계의 중요성을 깊이 느꼈습니다. 특히, 각 기능(룰렛, 리뷰 관리, 파일 입출력 등)을 체계적으로 설계하고 이를 GUI와 연동하는 과정에서,
사전에 명확한 계획과 구조를 세우는 것이 얼마나 중요한지 알게 되었습니다. 잘 구성된 설계는 구현 과정에서의 혼란을 줄이고, 프로그램의 유지보수와 확장성을 크게 향상시킨다는 점을 배울 수 있었습니다.
