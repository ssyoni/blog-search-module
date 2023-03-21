package modules.be.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class Pagination {
    public int totalCount;      // 총 검색 결과
    public int size;            // 한 페이지당 출력 갯수
    public int currentPage;     //다음 페이지
    public int totalPage;       // 총 페이지 갯수

    @Builder
    public Pagination(int totalCount, int size, int currentPage){
        this.totalCount = totalCount;
        this.size = size;
        this.currentPage = currentPage;
        this.totalPage = ((totalCount - 1) / size ) + 1;
    }
}
