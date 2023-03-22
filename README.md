# API Document

🔗 jar 파일 다운로드 


---

### version

springboot

# 공통사항

## Response Body

**code**

| Name | Type | Description |
| --- | --- | --- |
| code | String | HTTP 코드값 |

**status**

| Name | Type | Description |
| --- | --- | --- |
| status | Integer | HTTP 상태값 |

**data**

| Name | Type | Description |
| --- | --- | --- |
| data | Object | 응답 데이터 |

# 블로그 검색 API

**기본 정보**

```bash
GET /api/blog/search
```

카카오 블로그 검색 API를 호출해 질의어로 게시물을 검색합니다. 카카오 서버 장애 발생시 네이버 블로그 검색API를 호출하여 검색합니다. 질의어 외에 결과 형식 파라미터를 선택적으로 추가하여 검색할 수 있습니다. 응답 바디는 `code`, `status`, `page`, `data`로 넘어옵니다. data 안에 `page` 와 `blogs` 정보들이 List 형식으로 넘어옵니다.

## Request

### Parameter

| Name | Type | Description | Required |
| --- | --- | --- | --- |
| keyword | String | 검색을 원하는 질의어특정 블로그 글만 검색하고 싶은 경우, 블로그 url과 검색어를 공백(' ') 구분자로 넣을 수 있음 | O |
| pageSort | String | 결과 문서 정렬 방식, ACCURACY(정확도순) 또는 RECENCY(최신순), 기본 값 ACCURACY | X |
| pageSize | Integer | 결과 페이지 번호, 1~50 사이의 값, 기본 값 1 | X |
| searchPage | Integer | 한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10 | X |

### Response

**blogs**

| Name | Type | Description |
| --- | --- | --- |
| title | String | 블로그 글 제목 |
| contents | String | 블로그 글 요약 |
| url | String | 블로그 글 URL |
| blogname | String | 블로그의 이름 |
| thumbnail | String | 검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음 |
| date | String | 블로그 글 작성시간, [YYYY]-[MM]-[DD] |

**page**

| Name | Type | Description |
| --- | --- | --- |
| totalCount | Integer | 검색된 문서 수 |
| size | Integer | 노출 가능한 문서 수 |
| currentPage | Integer | 현재 조회한 페이지 수 |
| totalPage | Integer | 총 페이지 수 |

## Sample

**Request**

```bash
curl -v -X POST "http://localhost:8080/api/blog/search"\
--header 'Content-type:application/json' \
--data '{\
    "keyword":"카카오뱅크",\
    "pageSort" : "ACCURACY",\
    "pageSize" : 10,\
    "searchPage" : 1\
}'\
```

**Response**

```json
{
    "code": "DONE",
    "status": 200,
    "data": {
        "blogs": [
            {
                "title": "<b>카카오</b><b>뱅크</b> 전월세보증금 총정리",
                "contents": "그랬던 것처럼 보증금 낼 돈이 500만 원이 전부라면 어떻게 해야 할까요. 5분만 아니 3분만 보시면 두 번째 집으로 이사 가실 수 있게 도와드리겠습니다. <b>카카오</b><b>뱅크</b> 전월세보증금 총정리 공인중개사 현직에서 일하다 보면 많은 분들이 <b>카카오</b><b>뱅크</b> 전월세보증금에 대해 문의하십니다. 잠깐 살펴보면 너무 단순하고 간편...",
                "url": "http://hwshare07.com/48",
                "blogname": "하운드쉐어",
                "thumbnail": "",
                "date": "2023-03-19"
            },
            {
                "title": "<b>카카오</b> <b>뱅크</b> 적금 추천",
                "contents": "특판 상품도 점차 사라져 가고 있습니다. 요즘은 간편하게 가입하고 유지 조건도 까다롭지 않으면서 금리가 점점 더 어려워지고 있습니다. 반면, <b>카카오</b><b>뱅크</b>는 대한민국 국민이라면 대부분 보유하고 있지 않을까 싶습니다. 오늘은 가장 인기 있는 <b>카카오</b> <b>뱅크</b> 적금에 대해서 자세히 알아보겠습니다. 목차 1. <b>카카오</b> <b>뱅크</b>...",
                "url": "http://d.yesomryu.com/10",
                "blogname": "KOREA DAILY NEWS",
                "thumbnail": "",
                "date": "2023-03-06"
            },
            {
                "title": "<b>카카오</b><b>뱅크</b> 계좌 개설 방법 (총정리)",
                "contents": "<b>카카오</b><b>뱅크</b> 계좌 개설 방법을 순서대로 정리해 보겠습니다. 가족과 지인들이 <b>카카오</b><b>뱅크</b>를 사용하고 있어서 함께 모임통장을 만드려고 이번에 <b>카카오</b><b>뱅크</b> 계좌 개설을 해 보았습니다. 약 5분 정도만 시간을 투자하면 손쉽게 만들 수 있습니다. <b>카카오</b><b>뱅크</b> (Kakaobank) <b>카카오</b><b>뱅크</b>는 은행지점이 없는 온라인 전용 은행...",
                "url": "http://mellowed.co.kr/28",
                "blogname": "라이프 팩토리",
                "thumbnail": "https://search4.kakaocdn.net/argon/130x130_85_c/F7grKQ6fG9m",
                "date": "2023-03-06"
            }
				...
        ],
        "page": {
            "totalCount": 800,
            "size": 10,
            "currentPage": 1,
            "totalPage": 267
        }
    }
}
```

---

# 인기검색어 조회 API

**기본 정보**

```bash
GET /api/blog/hotkeyword
```

사용자가 많이 검색한 순으로 인기 검색어를 최대 10개 조회합니다. 

### Response

**hotKeywords**

| Name | Type | Description |
| --- | --- | --- |
| keyword | String | 검색어 |
| score | Long | 검색된 횟수 |

## Sample

**Request**

```bash
curl -v -X GET "http://localhost:8080/api/hotkeywords" 
```

**Response**

```json
{
    "code": "DONE",
    "status": 200,
    "data": {
        "hotKeywords": [
            {
                "keyword": "오늘 점심 뭐먹지?",
                "score": 16
            },
            {
                "keyword": "카뱅",
                "score": 15
            },
            {
                "keyword": "애플페이",
                "score": 13
            },
            {
                "keyword": "로또 번호",
                "score": 11
            },
            {
                "keyword": "오늘 날씨",
                "score": 11
            },
            {
                "keyword": "미세먼지",
                "score": 6
            },
            {
                "keyword": "카카오뱅크",
                "score": 3
            },
            {
                "keyword": "카카오뱅크 백엔드 합격 후기",
                "score": 3
            },
            {
                "keyword": "눈썹 문신",
                "score": 3
            },
            {
                "keyword": "카뱅 합격 후기",
                "score": 2
            }
        ]
    }
}
```

---

# 사용한 라이브러리

| 라이브러리 | 사용 모듈 | 설명 |
| --- | --- | --- |
| com.fasterxml.jackson.core:jackson-core | root | json 과 Object 간의 변환 등의 목적으로 사용 |
| com.h2database:h2 | domain | 백엔드 서버 데이터베이스로 사용 |
| org.apache.httpcomponents:httpclient | client | HttpComponentsClientHttpRequestFactory 객체 사용하여 RestTemplate 설정 주입하기 위해 사용 |
| org.springframework.boot:spring-boot-starter-validation | client, api | 스프링에서 제공하는 벨리데이션 어노테이션 사용 |

그 외  `org.projectlombok:lombok`, `org.springframework.boot:spring-boot-starter-data-jpa` 등을 사용했습니다.
