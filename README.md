# 소개
기존에 ![스프링](https://img.shields.io/badge/SpringFramework-6DB33F?style=for-the-badge&logo=spring&logoColor=white)를 이용해 구현한 프로젝트를 ![스프링](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)와![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)을 활용하여 재개발한 프로젝트입니다.

# 제작동기💊
- 약의 특징(글자, 색, 모양, 재형 등)을 선택하여 약 정보를 쉽게 검색할 수 있는 약학정보원 홈페이지의 분류 방식을 참고하여, 사용자가 손쉽게 원하는 상품을 찾을 수 있는 쇼핑몰 형태의 홈페이지를 기획하였습니다.
![image](https://github.com/jiyooya/TIM/assets/127083635/a4c6707b-e926-4f9a-917d-8941ad519353)

# 목표
- 샌드위치 프렌차이즈의 메뉴 커스터마이징 방식과 샐러드 정기 배송 서비스를 제공하는 홈페이지를 벤치마킹하여, 다양한 메뉴를 나만의 레시피로 편리하게 주문하고 즐길 수 있는 샐러드 주문 전용 홈페이지를 구축하였습니다.
![image](https://github.com/jiyooya/TIM/assets/127083635/bd6a8bd2-c3f0-4e77-9a46-da84b0a16950)

# 개발환경
![image](https://github.com/jiyooya/TIM/assets/127083635/b5b98047-d3ce-41d6-885e-e8d97553dee1)

# ER 다이어그램
![image](https://github.com/jiyooya/TIM/assets/127083635/a7926940-a2c0-440e-b096-d9f452f72f5a)

# 메뉴 구조
![메뉴구조도](https://github.com/jiyooya/TIM/assets/127083635/a55e6c78-d3dd-4d31-93f1-3d4951fd206f)

# 맡은 역할
- 제가 맡은 역할은 로그인한 사용자가 상세 제품 페이지 또는 장바구니 페이지에서 선택한 매장과 상품 정보, 결제 금액, 그리고 적립될 포인트 데이터를 수집하고, 이를 결제 페이지로 전달하는 작업이었습니다.

# 결제페이지
![데이터 처리](https://img.shields.io/badge/데이터처리-39457E?style=for-the-badge&logoColor=white)
- 로그인한 사용자의 ID와 관련된 포인트, 그리고 선택한 매장 정보를 불러옵니다. 사용자가 '포인트 전체 사용' 버튼을 클릭하면, 해당 기능을 수행하는 함수가 호출됩니다. 이 함수는 사용자의 보유 포인트를 매개변수로 받아, 사용자의 포인트를 전체 사용 상태로 변경합니다.
![결제](https://github.com/jiyooya/TIM/assets/127083635/37a318d2-c855-4508-bd64-e84e4788b6d4)
![할인 및 포인트](https://github.com/jiyooya/TIM/assets/127083635/1f150935-dbc7-4f2f-8539-f108180db195)


### 결제 방법
- 두 가지 결제 방법을 제공합니다. '무통장 입금' 버튼을 클릭하면 해당 기능을 수행하는 함수가 호출되어 사용자에게 입금 폼을 보여줍니다. 무통장 입금을 선택한 사용자는 '영수증 발급' 버튼을 클릭하여 소득 공제를 위한 정보를 입력할 수 있는 폼에 접근할 수 있습니다. 영수증 발급을 원치 않는 경우 '발급 안함' 버튼을 클릭하면, 영수증 발급 관련 폼이 삭제됩니다. 만약 사용자가 결제 방법을 '카카오 결제'로 변경하고자 할 경우, '카카오 결제' 버튼을 클릭하면 무통장 입금 관련 폼이 삭제되는 기능을 추가하였습니다.


![image](https://github.com/jiyooya/TIM/assets/127083635/63fecc1f-72cb-41e6-a580-78464292fa4e)

# 주문내역
![데이터 표시](https://img.shields.io/badge/데이터표시기능-39457E?style=for-the-badge&logoColor=white)
- 장바구니 페이지 또는 상세 제품 페이지에서 선택한 상품명, 사용자가 직접 선택한 재료, 수량, 금액 등의 정보를 수집하여 화면에 표시합니다. 사용자가 보유한 포인트를 사용하기로 결정하면, 콜백 함수를 통해 총 주문금액에서 사용 포인트를 제외한 최종 결제금액을 계산하고 이를 화면에 표시하도록 구현하였습니다.

![상세 페이지 데이터 표시](https://img.shields.io/badge/상세페이지데이터표시-D83B01?style=for-the-badge&logo=microsoft-office&logoColor=white)
![상세 페이지 주문내역](https://github.com/jiyooya/project/assets/127083635/ce18a732-703b-4d27-bc7c-1f9e5b8c89db)

![장바구니](https://img.shields.io/badge/장바구니페이지데이터표시-43853D?style=for-the-badge&logoColor=white)
![장바구니 주문내역](https://github.com/jiyooya/project/assets/127083635/ce4cc2d3-ce9d-439e-9073-1404e29e199b)

- 구매 조건에 동의하는 체크박스에 체크하지 않은 상태에서 '결제하기' 버튼을 클릭하면, 사용자에게 동의를 요청하는 안내 메시지가 출력되도록 설정하였습니다. 또한, 동의 체크박스에 체크한 후 '결제하기' 버튼을 누를 경우, 주문 취소가 불가능하다는 사실을 알리는 안내 메시지가 추가로 표시되도록 하였습니다.

![구매동의](https://github.com/jiyooya/TIM/assets/127083635/b447afa0-9c42-43fb-99ea-6a041a82c3be)

![카카오](https://img.shields.io/badge/결제QR-F7DF1E?style=for-the-badge&logoColor=white)

- '결제하기' 버튼을 클릭하면 카카오 QR코드가 출력되며, 결제가 성공적으로 이루어지면 결제 성공에 대한 안내 메시지가 표시되고 이후에는 자동으로 다음 페이지로 이동하게 됩니다.

![카카오큐알](https://github.com/jiyooya/project/assets/127083635/dc3aaa31-89c2-4771-9cc7-87f530d23dc2)

# 주문완료페이지
- 자바의 Random 함수를 활용하여 주문번호를 생성하도록 구현하였습니다. 결제 페이지와 마찬가지로 매장 정보, 사용자 ID 정보, 그리고 주문 내역을 데이터로 받아 왔습니다. 마지막으로, 결제 금액에 대한 일정 비율의 포인트가 적립되도록 기능을 추가하였습니다.

![주문 상세 내역](https://github.com/jiyooya/project/assets/127083635/93253aa7-fb48-44b6-9f68-2f6429fbef18)
![포인트 적립](https://github.com/jiyooya/project/assets/127083635/449602da-0639-40d1-8e42-e7911768093f)


# 결제페이지 시연 영상
(용량 문제로 화질이 좋지 않은 점 양해 부탁드립니다.)
![결제페이지 영상](https://github.com/jiyooya/TIM/assets/127083635/e51c6897-172c-43b1-92e4-954fab35d7fd)



