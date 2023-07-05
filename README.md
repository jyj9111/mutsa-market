# ♻️ 멋사마켓 - _중고 거래 플랫폼_

##### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_from:&nbsp; . / 멋쟁이 사자처럼 / 백엔드 스쿨 / 5th / 미니 프로젝트 1_

## 프로젝트 소개
- **♻️ 멋사마켓**은 많은 사람들이 사용 중인 **🥕당근마켓, 🧱중고나라** 에 착안하여 자신만의 중고거래 플랫폼을 만드는 미니 프로젝트 입니다.  
- 사용자가 중고 물품을 올리고, 댓글을 통해 소통하며, 구매 제안을 수락할 수 있는 중고거래 플랫폼의 백엔드를 구성 하였습니다.  

### 참고 ERD
<br>
<img width="400" alt="db_erd" src="https://github.com/likelion-backend-5th/MiniProject_Basic_JangYongJin/assets/130991633/365a4264-0c60-4dda-a5b3-6d665b37992e">
<br>

## 구현 기능
### 간략 구성도
이하 생략 URL : _http://{도메인 주소}_  ex) http://localhost:8080
#### - Sales_Item
중고 거래할 물품을 (판매자) 등록, 수정, 이미지 등록, 삭제 (모두) 전체 조회, 단일 조회 

| 요청 | Method | Mapping URL |
|:-- | :--: | :-- |
| 물품 등록 | POST | /items |
| 물품 수정 | PUT | /items/{itemId} |
| 물품 이미지 등록 | PUT | /items/{itemId}/image |
| 물품 삭제 | DELETE | /items/{itemId} |
| 물품 전체 조회 | GET | /items?page={페이지 번호}&limit={물품 갯수} |
| 물품 단일 조회 | GET | /items/{itemsId} |

  
#### - Comments
해당 물품에 대한 댓글을 (구매자) 등록, 수정, 삭제 (판매자) 답글 등록, (모두) 댓글 전체 조회
| 요청 | Method | Mapping URL |
|:-- | :--: | :-- |
| 댓글 등록 | POST | /items/{itemId}/comments |
| 댓글 수정 | PUT | /items/{itemId}/comments/{commentId} |
| 댓글 삭제 |  DELETE | /items/{itemId}/comments/{commentId} |
| 답글 등록 | PUT | /items/{itemId}/comments/{commentId}/reply |
| 댓글 전체 조회 | GET | /items/{itemId}/comments} |

#### - Negotiation
해당 물품에 대한 구매제안을 (구매자) 등록, 수정, 삭제, 구매 확정 (판매자) 수락 or 거절 (모두) 조회
| 요청 | Method | Mapping URL |
|:-- | :--: | :-- |
| 제안 등록 | POST | /items/{itemId}/proposals |
| 제안 수정 | PUT | /items/{itemId}/proposals/{proposalId} |
| 제안 삭제 |  DELETE | /items/{itemId}/proposals/{proposalId} |
| 구매 확정 | PUT | /items/{itemId}/proposals/{proposalId} |
| 제안 수락or거절 | PUT | /items/{itemId}/proposals/{proposalId} |
| 제안 조회 | GET | /items/{itemId}/proposals/{proposalId}?writer={작성자}&password={비밀번호}&page={페이지 번호}|
<br/>

### 상세 설명
#### Sales_Item
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


