package uk.co.strangeskies.gears.entities;

import uk.co.strangeskies.gears.entity.management.EntityManager;
import uk.co.strangeskies.gears.entity.management.impl.collections.EntityManagerImpl;
import uk.co.strangeskies.gears.entity.scheduling.schedulers.PeriodicScheduler;
import uk.co.strangeskies.gears.entity.scheduling.terminating.schedulers.LinearScheduler;

public class Test2 {
	EntityManager entityManager;

	public Test2() {
		entityManager = new EntityManagerImpl();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void run() {
		PeriodicScheduler scheduler = new PeriodicScheduler(new LinearScheduler());
		scheduler.setPeriodFrequency(5);
		getEntityManager().behaviour().setDefaultScheduler(scheduler);
		/*-
		 final StateComponent<Vector2<DoubleValue>> position = StateComponent
		 .<Vector2<DoubleValue>> builder().name("Position")
		 .description("Position of entity")
		 .dataFactory(new CopyFactory<>(new Vector2<>(DoubleValue.factory())))
		 .create();

		 final StateComponent<Vector2<DoubleValue>> velocity = StateComponent
		 .<Vector2<DoubleValue>> builder().name("Velocity")
		 .description("Velocity of entity")
		 .dataFactory(new CopyFactory<>(new Vector2<>(DoubleValue.factory())))
		 .create();

		 final BehaviourComponent movement = BehaviourComponent.builder()
		 .name("Movement").description("Basic movement of entity")
		 .readDependencies(velocity).writeDependencies(position)
		 .process(new BehaviourComponentProcess() {
		 @Override
		 public void process(Set<? extends Entity> entities,
		 EntityManagerProcessingContext context) {
		 for (Entity entity : entities) {
		 context.getStateManager().getData(entity, position)
		 .add(context.getStateManager().getData(entity, velocity));
		 }
		 }
		 }).create();
		 getEntityManager().getBehaviourManager().addUniversal(movement);

		 final BehaviourComponent reportPosition = BehaviourComponent.builder()
		 .name("Movement").description("Basic movement of entity")
		 .readDependencies(position).process(new BehaviourComponentProcess() {
		 @Override
		 public void process(Set<? extends Entity> entities,
		 EntityManagerProcessingContext context) {
		 for (Entity entity : entities) {
		 System.out.println(context.getStateManager()
		 .getData(entity, position).toString());
		 }
		 }
		 }).create();
		 getEntityManager().getBehaviourManager().addUniversal(reportPosition);

		 reportPosition.addBehaviourDependencies(movement);

		 Entity entity1 = getEntityManager().create();
		 getEntityManager().getStateManager().attach(entity1, position);
		 getEntityManager().getStateManager().attachAndSet(entity1, velocity,
		 new Vector2<>(DoubleValue.factory(), 1, 2));

		 Entity entity2 = getEntityManager().create();
		 getEntityManager().getStateManager().attach(entity2, position);

		 getEntityManager().startProcessing();
		 synchronized (this) {
		 try {
		 wait(2000);
		 } catch (InterruptedException e) {
		 e.printStackTrace();
		 }
		 }
		 getEntityManager().stopProcessing();*/
	}

	public static void main(String... args) {
		new Test2().run();
	}
}
