# ♻️ 멋사마켓 - _중고 거래 플랫폼_

##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_from:&nbsp; . / 멋쟁이 사자처럼 / 백엔드 스쿨 / 5th / 미션형 프로젝트_1_

## 1️⃣ 프로젝트 소개
- **♻️ 멋사마켓**은 많은 사람들이 사용 중인 **🥕당근마켓, 🧱중고나라** 에 착안하여 자신만의 중고거래 플랫폼을 만드는 미니 프로젝트 입니다.  
- 사용자가 중고 물품을 올리고, 댓글을 통해 소통하며, 구매 제안을 수락할 수 있는 중고거래 플랫폼의 백엔드를 구성 하였습니다.
</br>

### ⚙️ 개발 환경
- `Framework : Spring Boot 3.1.1`
- `Language : Java 17`
- `IDE : IntelliJ IDEA`
- `DataBase : SQLite`
  - `ORM (Object Relational Mapping) : JPA (Java Persistent API)`
- `Security : Spring Security`
</br>

### 📄 참고 ERD
<br>
<img width="450" alt="db_erd" src="https://github.com/likelion-backend-5th/MiniProject_Basic_JangYongJin/assets/130991633/365a4264-0c60-4dda-a5b3-6d665b37992e">
<br>
</br>

## 2️⃣ 구현 기능

<details>
<summary>
    
  ### 1. Endpoint 정리
</summary>
===========================================================================
<details>
  <summary>
    
  #### a. 회원가입(Register Member)
  </summary>
<div markdown="1">

_회원 가입 진행_
| 요청 | Method | Mapping URL |
|:-- | :--: | :-- |
| 회원 가입 | POST | /users/register |
  
</div>
</details>
<details>
  <summary>
    
  #### b. 로그인(Login)
  </summary>
<div markdown="1">
_로그인 진행_
  | 요청 | Method | Mapping URL |
  |:-- | :--: | :-- |
  | 로그인 | POST | /users/login |
  
</div>
</details>
<details>
  <summary>
    
  #### c. 상품(Sales_Item)
  </summary>
<div markdown="1">

_중고 거래할 물품을 (판매자) 등록, 수정, 이미지 등록, 삭제 (모두) 전체 조회, 단일 조회_
| 요청 | Method | Mapping URL |
|:-- | :--: | :-- |
| 상품 등록 | POST | /items |
| 상품 수정 | PUT | /items/{itemId} |
| 상품 이미지 등록 | PUT | /items/{itemId}/image |
| 상품 삭제 | DELETE | /items/{itemId} |
| 상품 전체 조회 | GET | /items/read |
| 상품 단일 조회 | GET | /items/read/{itemsId} |
  
</div>
</details>
<details>
  <summary>
    
  #### d. 댓글(Comments)
  </summary>
<div markdown="1">

_해당 물품에 대한 댓글을 (구매자) 등록, 수정, 삭제 (판매자) 답글 등록, (모두) 댓글 전체 조회_
| 요청 | Method | Mapping URL |
|:-- | :--: | :-- |
| 댓글 등록 | POST | /items/{itemId}/comments |
| 댓글 수정 | PUT | /items/{itemId}/comments/{commentId} |
| 댓글 삭제 |  DELETE | /items/{itemId}/comments/{commentId} |
| 답글 등록 | PUT | /items/{itemId}/comments/{commentId}/reply |
| 댓글 전체 조회 | GET | /items/{itemId}/comments/read |
  
</div>
</details>
<details>
  <summary>
    
  #### e. 구매 제안(Negotiation)
  </summary>
<div markdown="1">

_해당 물품에 대한 구매제안을 (구매자) 등록, 수정, 삭제, 구매 확정 (판매자) 수락 or 거절 (모두) 조회_
| 요청 | Method | Mapping URL |
|:-- | :--: | :-- |
| 제안 등록 | POST | /items/{itemId}/proposals |
| 제안 수정 | PUT | /items/{itemId}/proposals/{proposalId} |
| 제안 삭제 |  DELETE | /items/{itemId}/proposals/{proposalId} |
| 제안 수락or거절 | PUT | /items/{itemId}/proposals/{proposalId} |
| 구매 확정 | PUT | /items/{itemId}/proposals/{proposalId} |
| 제안 조회 | GET | /items/{itemId}/proposals/{proposalId}?page={페이지 번호}|
  
</div>
</details>
===========================================================================
</details>

<details>
  <summary>

  ### 2. 상세 API 명세
  </summary>
===========================================================================
  <details>
  <summary>
    
  #### a. 회원가입(Register Member)
  </summary>

  <details>
    <summary> 회원가입 - 성공 </summary>
<div markdown="1">

- 요청<br/>
  - `POST /users/register`
  - Header :
    ```javascript
    // 추가요소 없음
    ```
  - Body :
  ```json
  {
    "username" : "gaga",
    "password" : "1234",
    "passwordCheck" : "1234",
    "email" : "gaga@gmail.com",
    "phone" : "010-1588-1588",
    "city" : "Suwon"
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "회원가입이 완료되었습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary> 회원가입 - 비밀번호 불일치 </summary>
<div markdown="1">

- 요청<br/>
  - `POST /users/register`
  - Header :
    ```javascript
    // 추가요소 없음
    ```
  - Body :
  ```json
  {
    "username" : "gaga",
    "password" : "1234",
    "passwordCheck" : "5678",
    "email" : "gaga@gmail.com",
    "phone" : "010-1588-1588",
    "city" : "Suwon"
  }
  ```
- 응답<br/>
  - Status : 400
  - Body :
  ```json
  {
    "message": "비밀번호가 일치하지 않습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary> 회원가입 - 이미 존재하는 이름 </summary>
<div markdown="1">

- 요청<br/>
  - `POST /users/register`
  - Header :
    ```javascript
    // 추가요소 없음
    ```
  - Body :
  ```json
  {
    "username" : "gaga",
    "password" : "1234",
    "passwordCheck" : "1234",
    "email" : "gaga@gmail.com",
    "phone" : "010-1588-1588",
    "city" : "Suwon"
  }
  ```
- 응답<br/>
  - Status : 400
  - Body :
  ```json
  {
    "message": "이미 존재하는 이름 입니다."
  }
  ```

</div>
  </details>
</details>
<details>
  <summary>
    
  #### b. 로그인(Login)
  </summary>

  <details>
    <summary> 로그인 - 성공(jwt 토큰 발급) </summary>
<div markdown="1">

- 요청<br/>
  - `POST /users/login`
  - Header :
    ```javascript
    // 추가요소 없음
    ```
  - Body :
  ```json
  {
    "username" : "gaga",
    "password" : "1234"
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJnYWdhIiwia.BfM5nLtLs3aFPNL8Amy-IJhUBG6G1tOwn6vAhUjMdE-"
  }
  ```

</div>
  </details>
  <details>
    <summary> 로그인 - 비밀번호 불일치 </summary>
<div markdown="1">

- 요청<br/>
  - `POST /users/login`
  - Header :
    ```javascript
    // 추가요소 없음
    ```
  - Body :
  ```json
  {
    "username" : "gaga",
    "password" : "5678"
  }
  ```
- 응답<br/>
  - Status : 400
  - Body :
  ```json
  {
    "message": "비밀번호가 일치하지 않습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary> 로그인 - 존재하지 않는 유저 </summary>
<div markdown="1">

- 요청<br/>
  - `POST /users/login`
  - Header :
    ```javascript
    // 추가요소 없음
    ```
  - Body :
  ```json
  {
    "username" : "nana",
    "password" : "1234"
  }
  ```
- 응답<br/>
  - Status : 400
  - Body :
  ```json
  {
    "message": "등록되지 않은 사용자 입니다."
  }  
  ```

</div>
  </details>
</details>

<details>
  <summary>
    
  #### a. 상품(Sales_Item)
  </summary>
  <details>
    <summary>상품 등록</summary>
<div markdown="1">

- 요청<br/>
  - `POST /items`
  - Header :
  ```javascript
    // 추가
    "authorization" :  "{ jwt-token }"
  ```
  - Body :
  ```json
  {
    "title": "중고 맥북 팝니다",
    "description": "2019년 맥북 프로 13인치 모델입니다",
    "minPriceWanted": 1000000
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "등록이 완료되었습니다. "
  }
  ```

</div>
  </details>
  <details>
    <summary>상품 수정</summary>
<div markdown="1">

- 요청<br/>
  - `PUT /items/1`
  - Header :
  ```javascript
    // 추가
    "authorization" :  "{상품 등록 유저의 jwt-token }"
  ``` 
  - Body :
  ```json
  {
    "title": "중고 맥북 팝니다",
    "description": "2019년 맥북 프로 13인치 모델입니다",
    "minPriceWanted": 1260000
  }  
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "물품이 수정되었습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>상품 이미지 등록</summary>
<div markdown="1">

- 요청<br/>
  - `PUT /items/1/image`
  - Header :
  ```javascript
    // 추가
    "authorization" :  "{상품 등록 유저의 jwt-token }"
  ``` 
  - Body :
  ```json
  // form-data 
  "image" = 사진.png
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "이미지가 등록되었습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>상품 삭제</summary>
<div markdown="1">

- 요청<br/>
  - `DELETE /items/1`
  - Header :
  ```javascript
    // 추가
    "authorization" :  "{상품 등록 유저의 jwt-token }"
  ``` 
  - Body :
  ```json
  {
    "writer": "jang.dev",
    "password": "1234"
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "물품을 삭제했습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>상품 전체 조회</summary>
<div markdown="1">

- 요청<br/>
  - `GET /items/read?page=0&limit=25`
  - Header : X  
  - Body : X
  
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "content": [
        {
            "id": 1,
            "username": "gaga",
            "title": "중고 맥북 팝니다",
            "description": "2019년 맥북 프로 13인치 모델입니다",
            "minPriceWanted": 1260000,
            "imageUrl": null,
            "status": "판매중",
            "comments": [
                {
                    "id": 1,
                    "username": "nana",
                    "content": "50,000원 정도 할인 가능하신가요?",
                    "reply": null
                },
                {
                    "id": 2,
                    "username": "nana",
                    "content": "할인 가능하신가요?",
                    "reply": null
                },
                {
                    "id": 3,
                    "username": "nana",
                    "content": "할인 가능하신가요?2",
                    "reply": null
                }
            ]
        },
        {
            "id": 2,
            "username": "nana",
            "title": "콜드브루 드립기 팝니다",
            "description": "미개봉 1회사용 제품입니다.",
            "minPriceWanted": 20000,
            "imageUrl": null,
            "status": "판매중",
            "comments": []
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 25,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 25,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
  }
  ```

</div>
  </details>
  <details>
    <summary>상품 단일 조회</summary>
<div markdown="1">

- 요청<br/>
  - `GET /items/read/1`
  - Header: X
  - Body : X

- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "id": 1,
    "username": "gaga",
    "title": "중고 맥북 팝니다",
    "description": "2019년 맥북 프로 13인치 모델입니다",
    "minPriceWanted": 1260000,
    "imageUrl": null,
    "status": "판매중",
    "comments": [
        {
            "id": 1,
            "username": "nana",
            "content": "50,000원 정도 할인 가능하신가요?",
            "reply": null
        },
        {
            "id": 2,
            "username": "nana",
            "content": "할인 가능하신가요?",
            "reply": null
        },
        {
            "id": 3,
            "username": "nana",
            "content": "할인 가능하신가요?2",
            "reply": null
        }
    ]
  }
  ```

</div>
  </details>
</details>
<details>
  <summary>
    
  #### b. 댓글(Comments)
  </summary>
  <details>
    <summary>댓글 등록</summary>
<div markdown="1">

- 요청<br/>
  - `POST /items/1/comments`
  - Header :
  ```javascript
    // 추가
    "authorization" :  "{유저의 jwt-token }"
  ``` 
  - Body :
  ```json
  {
    "content": "할인 가능하신가요?"
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "댓글이 등록되었습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>댓글 수정</summary>
<div markdown="1">

- 요청<br/>
  - `PUT /items/1/comments/1`
  - Header :
  ```javascript
    // 추가
    "authorization" :  "{댓글 등록 유저의 jwt-token }"
  ``` 
  - Body :
  ```json
  {
    "content": "50,000원 정도 할인 가능하신가요?"
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "댓글이 수정되었습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>댓글 삭제</summary>
<div markdown="1">

- 요청<br/>
  - `DELETE /items/1/comments/1`
  - Header :
  ```javascript
    // 추가
    "authorization" :  "{댓글 등록 유저의 jwt-token }"
  ``` 
  - Body : X
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "댓글을 삭제했습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>답글 등록</summary>
<div markdown="1">

- 요청<br/>
  - `PUT /items/1/comments/1/reply`
  - Header :
  ```javascript
    // 추가
    "authorization" :  "{상품 등록 유저의 jwt-token }"
  ```
  - Body :
  ```json
  {
    "writer": "jang.dev",
    "password": "1234",
    "reply": "안됩니다"
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "reply": "힘들 것 같습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>댓글 전체 조회</summary>
<div markdown="1">

- 요청<br/>
  - `GET /items/1/comments/read`
  - Header :
    ```javascript
    // 추가
    "page" : "0"
    ```
  - Body :
  ```json
  {
    "writer": "jang.dev",
    "password": "1234",
    "reply": "안됩니다"
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "content": [
        {
            "id": 1,
            "content": "50,000원 정도 할인 가능하신가요?",
            "reply": null
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageSize": 25,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 1,
    "totalPages": 1,
    "size": 25,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
  }
  ```

</div>
  </details>
</details>

<details>
  <summary>

  #### c. 구매제안(Negotiation)
  </summary>
  <details>
    <summary>제안 등록</summary>
<div markdown="1">

- 요청<br/>
  - `POST /items/1/proposals`
  - Header :
  ```javascript
    // 추가
    "authorization" :  "{유저의 jwt-token }"
  ``` 
  - Body :
  ```json
  {
    "suggestedPrice": 1200000
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "구매 제안이 등록되었습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>제안 수정</summary>
<div markdown="1">

- 요청<br/>
  - `PUT /items/1/proposals/1`
  - Header :
  ```javascript
    // 추가
    "mode" : "buyer"
    "authorization" :  "{제안 등록 유저의 jwt-token }"
  ```
  - Body :
  ```json
  {
    "suggestedPrice": 1180000
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "제안이 수정되었습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>제안 삭제</summary>
<div markdown="1">

- 요청<br/>
  - `DELETE /items/1/proposals/1`
  - Header :
  ```javascript
    // 추가
    "authorization" :  "{제안 등록 유저의 jwt-token }"
  ```
  - Body : X
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "제안을 삭제했습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>제안 수락 or 거절</summary>
<div markdown="1">

- 요청<br/>
  - `PUT /items/1/proposals/1`
  - Header :
  ```javascript
    // 추가
    "mode" : "seller"
    "authorization" :  "{상품 등록 유저의 jwt-token }"
  ```
  - Body :
  ```json
  {
    "status": "수락"
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "제안의 상태가 변경되었습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>구매 확정</summary>
<div markdown="1">

- 요청<br/>
  - `PUT /items/1/proposals/1`
  - Header :
  ```javascript
    // 추가
    "mode" : "end"
    "authorization" :  "{제안 등록 유저의 jwt-token }"
  ```
  - Body :
  ```json
  {
    "status": "확정"
  }
  ```
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "message": "구매가 확정되었습니다."
  }
  ```
  - 하나의 제안이 "확정"이 될시 나머지 제안들은 자동으로 "거절" 로 상태가 바뀐다.
  - 해당 물품의 상태가 "판매 완료"로 바뀐다.

</div>
  </details>
  <details>
    <summary>제안 조회</summary>
<div markdown="1">

1. [판매자]는 해당 상품에 대한 모든 제안을 확인 할수있다. 
- 요청(판매자)
  - `/items/1/proposals?page=0`
  - Header:
    ```javascript
    // 추가
    "authorization" :  "{상품 등록 유저의 jwt-token }"
  ```
  - Body : X

- 응답 (판매자)
  - Status : 200
  - Body :
  ```json
   {
    "content": [
        {
            "id": 1,
            "suggestedPrice": 1200000,
            "status": "제안"
        },
        {
            "id": 2,
            "suggestedPrice": 1000000,
            "status": "제안"
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageSize": 25,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "size": 25,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
  }
  ```
2. [구매자]는 해당물품에 대해 본인이 작성한 제안만 확인 할수있다.
- 요청(구매자)
  - `/items/1/proposals?page=0`
  - Header:
    ```javascript
    // 추가
    "authorization" :  "{제안 등록 유저의 jwt-token }"
  ```
  - Body : X

- 응답 (구매자)
  - Status : 200
  - Body :
  ```json
  {
    "content": [
        {
            "id": 2,
            "suggestedPrice": 1000000,
            "status": "제안"
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageSize": 25,
        "pageNumber": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalElements": 1,
    "totalPages": 1,
    "size": 25,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
  }
  ```
  
</div>
  </details>
</details>
===========================================================================
</details>


<details>
  <summary>
    
  ### 3. 예외 처리
  </summary>
===========================================================================
  <details>
  <summary>
    
  #### a. 회원가입(Register Member)
  </summary>
<div markdown="1">

  | 예외 클래스명 | 발생 상황 | Staus Code | 에러 메세지 |
  | :--: | :--: | :--: | :--: |
  | `ExistUsernameException()` | 회원가입시 이미 존재하는 이름을 등록하려고 할때 발생 | 400 - Bad Request | `"이미 존재하는 이름 입니다."` |
  | `NotMatchedPasswordException()` | 회원가입시 비밀번호와 비밀번호 확인이 불일치 할때 발생 | 400 - Bad Request | `"비밀번호가 일치하지 않습니다."` |
  
</div>
  </details>
  <details>
  <summary>
    
  #### b. 로그인(Login)
  </summary>
<div markdown="1">

  | 예외 클래스명 | 발생 상황 | Staus Code | 에러 메세지 |
  | :--: | :--: | :--: | :--: |
  | `NotExistUsernameException()` | 로그인시 등록되지 않은 이름으로 시도할때 발생 | 404 - Not Found | `"등록되지 않은 사용자 입니다."` |
  | `NotMatchedPasswordException()` | 로그인시 비밀번호가 일치하지 않을때 발생 | 400 - Bad Request | `"비밀번호가 일치하지 않습니다."` |
  
</div>
  </details>
  <details>
  <summary>
    
  #### c. 상품(Sales_Item)
  </summary>
<div markdown="1">

  | 예외 클래스명 | 발생 상황 | Staus Code | 에러 메세지 |
  | :--: | :--: | :--: | :--: |
  | `ItemNotFoundException()` | 요청한 상품이 존재하지 않을때 발생 | 404 - Not Found | `"해당 물품이 존재하지 않습니다."` |
  | `ImageUpdateException()` | 이미지 등록과정에서 내부적으로 오류 났을때 발생 | 500 - Iternal Server Error | `"이미지 등록과정에서 문제가 발생하였습니다."` |
  | `UnAuthItemEditException()` | 상품 등록자가 아닌 사람이 수정요청을 했을때 발생 | 400 - Bad Request | `"상품 등록자만 수정이 가능합니다."` |
  | `UnAuthItemDeleteException()` | 상품 등록자가 아닌 사람이 삭제요청을 했을때 발생 | 400 - Bad Request | `"상품 등록자만 삭제가 가능합니다."` |
  
</div>
  </details>
  <details>
  <summary>
    
  #### d. 댓글(Comments)
  </summary>
<div markdown="1">

  | 예외 클래스명 | 발생 상황 | Staus Code | 에러 메세지 |
  | :--: | :--: | :--: | :--: |
  | `CommentNotFoundException()` | 요청한 댓글이 존재하지 않을때 발생 | 404 - Not Found | `"해당 댓글이 존재하지 않습니다."` |
  | `UnAuthCommentEditException()` | 댓글 등록자가 아닌 사람이 수정요청을 했을때 발생 | 400 - Bad Request | `"댓글 등록자만 수정이 가능합니다."` |
  | `UnAuthCommentDeleteException()` | 댓글 등록자가 아닌 사람이 삭제요청을 했을때 발생 | 400 - Bad Request | `"댓글 등록자만 수정이 가능합니다."` |
  | `UnAuthCommentReplyException()` | 답글 등록자가 아닌 사람이 수정요청을 했을때 발생 | 400 - Bad Request | `"답글 등록자만 수정이 가능합니다."` |
  
</div>
  </details>
  <details>
  <summary>
    
  #### e. 구매제안(Negotiation)
  </summary>
<div markdown="1">

  | 예외 클래스명 | 발생 상황 | Staus Code | 에러 메세지 |
  | :--: | :--: | :--: | :--: |
  | `ProposalNotFoundException()` | 요청한 제안이 존재하지 않을때 발생 | 404 - Not Found | `"해당 제안이 존재하지 않습니다."` |
  | `UnAuthNegoEditException()` | 제안 등록자가 아닌 사람이 수정요청을 했을때 발생 | 400 - Bad Request | `"제안 등록자만 수정이 가능합니다."` |
  | `UnAuthNegoDeleteException()` | 제안 등록자가 아닌 사람이 삭제요청을 했을때 발생 | 400 - Bad Request | `"제안 등록자만 삭제가 가능합니다."` |
  | `UnAuthNegoAcceptException()` | 상품 등록자가 아닌 사람이 수락,거절 요청을 했을때 발생 | 400 - Bad Request | `"상품 등록자만 수락 또는 거절이 가능합니다."` |
  | `UnAuthNegoConfirmException()` | 제안 등록자가 아닌 사람이 구매확정 요청을 했을때 발생 | 400 - Bad Request | `"제안 등록자만 구매확정이 가능합니다."` |
  | `WrongStatusException()` | 제안 등록자가 구매확정 요청을 했을때의 상태가 [수락]이 아닐경우 발생 | 400 - Bad Request | `"제안 등록자만 구매확정이 가능합니다."` |
  
</div>
  </details>
===========================================================================
</details>
</br>

## 3️⃣ 별첨
- PostmanCollection 추가
  - `파일이름 : 멋사-미션형프로젝트-1-jyj.postman_collection.json`
- PR 링크 공유
  - https://github.com/likelion-backend-5th/PeerReview_11Team/pulls

