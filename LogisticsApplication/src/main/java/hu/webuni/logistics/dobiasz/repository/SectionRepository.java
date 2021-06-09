package hu.webuni.logistics.dobiasz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.logistics.dobiasz.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long>{
	//@Query("SELECT s FROM Section s WHERE s.id = transport_plan_id AND s.from_milestone_id = 1 OR s.to_milestone_id = 1")
	@Query("SELECT s FROM Section s WHERE s.transport_plan_id = :transportPlanId AND s.from_milestone.id = :milestoneId OR s.to_milestone.id = :milestoneId")
	List<Section> findByTransportPlanAndMilestone(long transportPlanId, long milestoneId);

	@Query("SELECT s FROM Section s WHERE s.transport_plan_id = :transportPlanId  s.number = :number")
	Optional<Section> findByTransportPlanIdAndNumber(long transportPlanId, int number);

	@Query("SELECT s FROM Section s WHERE s.from_milestone.id = :milestoneId OR s.to_milestone.id = :milestoneId")
	Optional<Section> findByMilestoneId(long milestoneId);

}
