# ♻️ 멋사마켓 - _중고 거래 플랫폼_

##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_from:&nbsp; . / 멋쟁이 사자처럼 / 백엔드 스쿨 / 5th / 미니 프로젝트 1_

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
</br>

### 📄 참고 ERD
<br>
<img width="450" alt="db_erd" src="https://github.com/likelion-backend-5th/MiniProject_Basic_JangYongJin/assets/130991633/365a4264-0c60-4dda-a5b3-6d665b37992e">
<br>
</br>

## 2️⃣ 구현 기능

<details>
  <summary>
    
  ### 1. 간략 구성도
  </summary>

### Sales_Item
<div markdown="1">

_중고 거래할 물품을 (판매자) 등록, 수정, 이미지 등록, 삭제 (모두) 전체 조회, 단일 조회_
| 요청 | Method | Mapping URL |
|:-- | :--: | :-- |
| 물품 등록 | POST | /items |
| 물품 수정 | PUT | /items/{itemId} |
| 물품 이미지 등록 | PUT | /items/{itemId}/image |
| 물품 삭제 | DELETE | /items/{itemId} |
| 물품 전체 조회 | GET | /items?page={페이지 번호}&limit={물품 갯수} |
| 물품 단일 조회 | GET | /items/{itemsId} |

</div>

### Comments
<div markdown="1">

_해당 물품에 대한 댓글을 (구매자) 등록, 수정, 삭제 (판매자) 답글 등록, (모두) 댓글 전체 조회_
| 요청 | Method | Mapping URL |
|:-- | :--: | :-- |
| 댓글 등록 | POST | /items/{itemId}/comments |
| 댓글 수정 | PUT | /items/{itemId}/comments/{commentId} |
| 댓글 삭제 |  DELETE | /items/{itemId}/comments/{commentId} |
| 답글 등록 | PUT | /items/{itemId}/comments/{commentId}/reply |
| 댓글 전체 조회 | GET | /items/{itemId}/comments} |

</div>

### Negotiation
<div markdown="1">

_해당 물품에 대한 구매제안을 (구매자) 등록, 수정, 삭제, 구매 확정 (판매자) 수락 or 거절 (모두) 조회_
| 요청 | Method | Mapping URL |
|:-- | :--: | :-- |
| 제안 등록 | POST | /items/{itemId}/proposals |
| 제안 수정 | PUT | /items/{itemId}/proposals/{proposalId} |
| 제안 삭제 |  DELETE | /items/{itemId}/proposals/{proposalId} |
| 구매 확정 | PUT | /items/{itemId}/proposals/{proposalId} |
| 제안 수락or거절 | PUT | /items/{itemId}/proposals/{proposalId} |
| 제안 조회 | GET | /items/{itemId}/proposals/{proposalId}?writer={작성자}&password={비밀번호}&page={페이지 번호}|

</div>
</details>

<details>
  <summary>

  ### 2. 상세 설명
  </summary>
  <details>
  <summary>
    
  #### Sales_Item
  </summary>
  <details>
    <summary>물품 등록</summary>
<div markdown="1">

- 요청<br/>
  - `POST /items`  
  - Body :
  ```json
  {
    "title": "중고 맥북 팝니다",
    "description": "2019년 맥북 프로 13인치 모델입니다",
    "minPriceWanted": 1000000,
    "writer": "jang.dev",
    "password": "1234"
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
    <summary>물품 수정</summary>
<div markdown="1">

- 요청<br/>
  - `PUT /items/1`  
  - Body :
  ```json
  {
    "title": "중고 맥북 팝니다",
    "description": "2019년 맥북 프로 13인치 모델입니다",
    "minPriceWanted": 1250000,
    "writer": "jang.dev",
    "password": "1234"
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
    <summary>물품 이미지 등록</summary>
<div markdown="1">

- 요청<br/>
  - `PUT /items/1/image`  
  - Body :
  ```javascript
  // form-data 
  "image" = 사진.png
  "wirter" = "jang.dev"
  "password" = "1234"
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
    <summary>물품 삭제</summary>
<div markdown="1">

- 요청<br/>
  - `DELETE /items/1`  
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
    <summary>물품 전체 조회</summary>
<div markdown="1">

- 요청<br/>
  - `GET /items?page=0&limit=25`  
  - Body : X
  
- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "content": [
        {
            "id": 1,
            "title": "중고 맥북 팝니다",
            "description": "2019년 맥북 프로 13인치 모델입니다",
            "minPriceWanted": 1250000,
            "imageUrl": "/static/1/item_1_2023-07-05T195759.634444900.png",
            "status": "판매중"
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
        "paged": true,
        "unpaged": false
    },
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
    "size": 25,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
  }
  ```

</div>
  </details>
  <details>
    <summary>물품 단일 조회</summary>
<div markdown="1">

- 요청<br/>
  - `GET /items/1`  
  - Body : X

- 응답<br/>
  - Status : 200
  - Body :
  ```json
  {
    "title": "중고 맥북 팝니다",
    "description": "2019년 맥북 프로 13인치 모델입니다",
    "minPriceWanted": 1250000,
    "imageUrl": "/static/1/item_1_2023-07-05T195759.634444900.png",
    "status": "판매중"
  }
  ```

</div>
  </details>
</details>
<details>
  <summary>
    
  #### Comments
  </summary>
  <details>
    <summary>댓글 등록</summary>
<div markdown="1">

- 요청<br/>
  - `POST /items/1/comments`
  - Body :
  ```json
  {
    "writer": "choi.edu",
    "password": "1234",
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
  - Body :
  ```json
  {
    "writer": "choi.edu",
    "password": "1234",
    "content": "할인 가능하신가요? 1000000 정도면 고려 가능합니다"
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
  - Body :
  ```json
  {
    "writer": "choi.edu",
    "password": "1234"
  }
  ```
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
    "message": "댓글에 답글이 추가되었습니다."
  }
  ```

</div>
  </details>
  <details>
    <summary>댓글 전체 조회</summary>
<div markdown="1">

- 요청<br/>
  - `GET /items/1/comments`
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
            "content": "할인 가능하신가요? 1000000 정도면 고려 가능합니다",
            "reply": "안됩니다"
        },
        {
            "id": 2,
            "content": "직거래 가능하신가요?",
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
        "pageNumber": 0,
        "pageSize": 25,
        "paged": true,
        "unpaged": false
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

</div>
  </details>
</details>

<details>
  <summary>

  #### Negotiation
  </summary>
  <details>
    <summary>제안 등록</summary>
<div markdown="1">

- 요청<br/>
  - `POST /items/1/proposals`
  - Body :
  ```json
  {
    "writer": "choi.edu",
    "password": "1234",
    "suggestedPrice": 1250000
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
  ```
  - Body :
  ```json
  {
    "writer": "choi.edu",
    "password": "1234",
    "suggestedPrice": 1200000
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
  - Body :
  ```json
  {
    "writer": "choi.edu",
    "password": "1234"
  }
  ```
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
  ```
  - Body :
  ```json
  {
    "writer": "jang.dev",
    "password": "1234",
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
  ```
  - Body :
  ```json
  {
    "writer": "choi.edu",
    "password": "1234",
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

1. 입력한 writer, password 값이 물품 등록자일 경우 해당 물품에 등록된 모든 제안을 볼수있다. 
- 요청(판매자)
  - `/items/1/proposals?writer=jang.dev&password=1234&page=0`
  - Body : X

- 응답 (판매자)
  - Status : 200
  - Body :
  ```json
  {
    "content": [
        {
            "id": 1,
            "suggestedPrice": 1250000,
            "status": "확정"
        },
        {
            "id": 2,
            "suggestedPrice": 1240000,
            "status": "거절"
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
        "paged": true,
        "unpaged": false
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
2. 입력한 writer, password 값이 제안 등록자일 경우 본인이 등록한 제안만 볼수있다.
- 요청(구매자)
  - `/items/1/proposals?writer=choi.edu&password=1234&page=0`
  - Body : X

- 응답 (구매자)
  - Status : 200
  - Body :
  ```json
  {
    "content": [
        {
            "id": 1,
            "suggestedPrice": 1250000,
            "status": "확정"
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
        "paged": true,
        "unpaged": false
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
</details>


<details>
  <summary>
    
  ### 3. 예외 처리
  </summary>

  ### Status 400
  <div markdown="1">
  
  | 예외 클래스명 | 발생 상황 | Staus Code | 에러 메세지 |
  | :--: | :--: | :--: | :--: |
  | NotMatchedWriterException() | 요청 "writer"값 불일치 시 발생 | 400 - Bad Request| "작성자가 일치하지 않습니다." |
  | NotMatchedPasswordException() | 요청 "password"값 불일치 시 발생 | 400 - Bad Request| "비밀번호가 일치하지 않습니다." |
  | WrongStatusException() | 제안의 상태가 "수락"이 아닌데 '구매 확정' 요청이 들어왔을 시 발생 | 400 - Bad Request| "현재 제안이 [수락] 상태가 아닙니다." |
  
  </div>
  
  ### Status 404
  <div markdown="1">
  
  | 예외 클래스명 | 발생 상황 | Staus Code | 에러 메세지 |
  | :--: | :--: | :--: | :--: |
  | ItemNotFoundException() | 해당 물품이 없을 경우 발생 | 404 - Not Found | "해당 물품이 존재하지 않습니다." |
  | CommentNotFoundException() | 해당 댓글이 없을 경우 발생 | 404 - Not Found | "해당 댓글이 존재하지 않습니다." |
  | ProposalNotFoundException() | 해당 제안이 없을 경우 발생 | 404 - Not Found | "해당 제안이 존재하지 않습니다." |
  
  </div>

  ### Status 500
  <div markdown="1">
  
  | 예외 클래스명 | 발생 상황 | Staus Code | 에러 메세지 |
  | :--: | :--: | :--: | :--: |
  | ImageUpdateException() | 물품 이미지 등록에 실패하였을 경우 발생 | 500 - Internal Server Error | "이미지 등록과정에서 문제가 발생하였습니다." |
  
  
  </div>
</details>

