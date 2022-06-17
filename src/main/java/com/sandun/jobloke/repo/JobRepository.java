package com.sandun.jobloke.repo;

import com.sandun.jobloke.job.JobPost;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<JobPost,Integer> {

    @Query(value = "select job_post.* from created_jobs inner join job_post on job_post.jobid = created_jobs.jobid where created_jobs.Username = ?1", nativeQuery = true)
    Optional<List<JobPost>> findJobsCreatedByUser(String username);

    @Modifying
    @Query(value = "insert into created_jobs(username,jobid) values (:username,:id)", nativeQuery = true)
    @Transactional
    void insertToAppliedJob(@Param("id") int jobid, @Param("username") String username);
}
