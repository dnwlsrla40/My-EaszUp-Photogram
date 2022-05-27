package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
    @Modifying  // DB에 변경을 주는 네이티브 퀘리에 필요!
    @Query(value="INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId);

    @Modifying
    @Query(value = "DELETE from subscribe WHERE fromUserId=:fromUserId AND toUserId = :toUserId", nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);


    @Query(value = "SELECT COUNT(*) from subscribe WHERE fromUserId=:principalId AND toUserId=:pageUserId", nativeQuery = true)
    int mSubscribeState(int principalId, int pageUserId);

    @Query(value = "SELECT COUNT(*) from subscribe WHERE fromUserId=:pageUserId", nativeQuery = true)
    int mSubscribeCount(int pageUserId);
}
