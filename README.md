# HD

* API: `http://localhost:8080/htmls`

<br>

#### 기능 개발 시 고려사항
1. 3개 사이트 크롤링 비동기 
   1. (stream parallel)
2. 크롤링 시 timeout 운영 처리 
   1. (1sec 초과 or 실패 시, 재시도 3회)
3. merge 된 문자열 오름차순 및 파싱 시 성능
   1. 중복제거와 알파벳, 숫자 분류에 Set 사용
   2. 별도 정렬 하지 않도록 우선순위큐 사용
4. Cache 적용
   1. 인메모리 기본 캐시

<br>

---

#### 최종 성능
- m1 13inch macbook pro 기준

* 최초: 1~ 2sec 내외
* 이후: 70~ 80ms 내외
