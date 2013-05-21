package uk.co.elionline.gears.entities;

import java.util.Random;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.assembly.Assemblage;
import uk.co.elionline.gears.entity.assembly.Assembler;
import uk.co.elionline.gears.entity.assembly.AssemblyContext;
import uk.co.elionline.gears.entity.assembly.StateInitialiser;
import uk.co.elionline.gears.entity.assembly.Variable;
import uk.co.elionline.gears.entity.assembly.impl.AssemblerImpl;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilderFactory;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcess;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.elionline.gears.entity.behaviour.impl.BehaviourComponentBuilderFactoryImpl;
import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.management.EntityStateManager;
import uk.co.elionline.gears.entity.management.impl.collections.EntityManagerImpl;
import uk.co.elionline.gears.entity.processing.Processor;
import uk.co.elionline.gears.entity.processing.impl.ProcessorImpl;
import uk.co.elionline.gears.entity.scheduling.schedulers.PeriodicScheduler;
import uk.co.elionline.gears.entity.scheduling.terminating.schedulers.LinearScheduler;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentBuilder;
import uk.co.elionline.gears.entity.state.StateComponentBuilderFactory;
import uk.co.elionline.gears.entity.state.impl.StateComponentBuilderFactoryImpl;
import uk.co.elionline.gears.mathematics.expressions.IdentityExpression;
import uk.co.elionline.gears.mathematics.geometry.matrices.Vector2;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.utilities.CopyFactory;
import uk.co.elionline.gears.utilities.Factory;

public class Test1 {
	private final EntityManager entityManager;
	private final BehaviourComponentBuilderFactory behaviourComponentBuilderFactory;
	private final StateComponentBuilderFactory stateComponentBuilderFactory;
	private final Assembler assembler;

	public Test1() {
		entityManager = new EntityManagerImpl();

		behaviourComponentBuilderFactory = new BehaviourComponentBuilderFactoryImpl();
		stateComponentBuilderFactory = new StateComponentBuilderFactoryImpl();

		assembler = new AssemblerImpl();
	}

	public EntityManager entities() {
		return entityManager;
	}

	public Assembler assembler() {
		return assembler;
	}

	public <D> StateComponentBuilder<D> getStateComponentBuilder() {
		return stateComponentBuilderFactory.begin();
	}

	public <D> BehaviourComponentBuilder getBehaviourComponentBuilder() {
		return behaviourComponentBuilderFactory.begin();
	}

	private void run() {
		PeriodicScheduler scheduler = new PeriodicScheduler(new LinearScheduler());
		scheduler.setPeriodFrequency(5);
		entities().behaviour().setDefaultScheduler(scheduler);

		final StateComponent<Vector2<DoubleValue>> position = this
				.<Vector2<DoubleValue>> getStateComponentBuilder().name("Position")
				.description("Position of entity")
				.dataFactory(new CopyFactory<>(new Vector2<>(DoubleValue.factory())))
				.create();

		final StateComponent<Vector2<DoubleValue>> velocity = this
				.<Vector2<DoubleValue>> getStateComponentBuilder().name("Velocity")
				.description("Velocity of entity").readDependencies(position)
				.dataFactory(new CopyFactory<>(new Vector2<>(DoubleValue.factory())))
				.create();

		final StateComponent<IdentityExpression<String>> parrot = this
				.<IdentityExpression<String>> getStateComponentBuilder().name("Parrot")
				.description("A parrot which knows a word")
				.dataFactory(new Factory<IdentityExpression<String>>() {
					@Override
					public IdentityExpression<String> create() {
						return new IdentityExpression<>("");
					}
				}).create();

		final BehaviourComponent movement = getBehaviourComponentBuilder()
				.name("Movement").description("Moves entity by velocity")
				.readDependencies(velocity).writeDependencies(position)
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext context) {
						for (Entity entity : context.getEntities()) {
							EntityStateManager stateManager = context.entities().state();

							System.out.println(stateManager.getData(entity, position).add(
									stateManager.getData(entity, velocity)));
						}
					}
				}).create();
		entities().behaviour().addUniversal(movement);

		final BehaviourComponent parrotting = getBehaviourComponentBuilder()
				.name("Parrotting").description("Parrot makes a noise")
				.readDependencies(parrot).process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext context) {
						for (Entity entity : context.getEntities()) {
							EntityStateManager stateManager = context.entities().state();

							System.out.println(stateManager.getData(entity, parrot).get());
						}
					}
				}).create();
		entities().behaviour().addUniversal(parrotting);

		final Assemblage assemblage1 = assembler().base().derive();
		final Variable<IntValue> numberVariable = new Variable<IntValue>() {
			@Override
			public IntValue create() {
				return new IntValue(new Random().nextInt());
			}
		};
		assemblage1.getVariables().add(numberVariable);
		assemblage1.getStates().add(position);
		assemblage1.getStates().add(parrot);
		assemblage1.getInitialisers(position).add(
				new StateInitialiser<Vector2<DoubleValue>>() {
					@Override
					public void initialise(Vector2<DoubleValue> data,
							AssemblyContext context) {
						data.set(new Vector2<>(DoubleValue.factory(), 5, 5));
					}
				});

		final Assemblage assemblage2 = assemblage1.derive();
		assemblage2.getStates().add(velocity);
		assemblage2.getInitialisers(position).add(
				new StateInitialiser<Vector2<DoubleValue>>() {
					@Override
					public void initialise(Vector2<DoubleValue> data,
							AssemblyContext context) {
						data.set(new Vector2<>(DoubleValue.factory(), 12, 12));
					}
				});
		assemblage2.getInitialisers(velocity).add(
				new StateInitialiser<Vector2<DoubleValue>>() {
					@Override
					public void initialise(Vector2<DoubleValue> data,
							AssemblyContext context) {
						data.set(context.getSupercontext().getData(position)
								.getSubtracted(new Vector2<>(DoubleValue.factory(), 2, 2)));
					}
				});
		assemblage1.getSubassemblages().add(assemblage2);

		assemblage1.getInitialisers(parrot).add(
				new StateInitialiser<IdentityExpression<String>>() {
					@Override
					public void initialise(IdentityExpression<String> data,
							AssemblyContext context) {
						try {
							data.set("Child velocity: "
									+ context.getSubcontext(assemblage2).getData(velocity)
									+ " num " + context.getValue(numberVariable));
						} catch (IllegalArgumentException e) {
							data.set("Parent num: "
									+ context.getSupercontext().getValue(numberVariable)
									+ " num: " + context.getValue(numberVariable));
						}
					}
				});

		assembler().assemble(assemblage1, entities());
		assembler().assemble(assemblage1, entities());

		Processor processor = new ProcessorImpl();
		processor.startProcessing(entities());
		try {
			synchronized (this) {
				wait(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		processor.stopProcessing();
	}

	public static void main(String... args) {
		new Test1().run();
	}
}
