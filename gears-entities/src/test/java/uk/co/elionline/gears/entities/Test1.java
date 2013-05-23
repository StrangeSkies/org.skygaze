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
import uk.co.elionline.gears.entity.behaviour.BehaviourProcess;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.elionline.gears.entity.behaviour.impl.BehaviourComponentBuilderImpl;
import uk.co.elionline.gears.entity.management.EntityManager;
import uk.co.elionline.gears.entity.management.EntityStateManager;
import uk.co.elionline.gears.entity.management.impl.collections.EntityManagerImpl;
import uk.co.elionline.gears.entity.processing.Processor;
import uk.co.elionline.gears.entity.processing.impl.ProcessorImpl;
import uk.co.elionline.gears.entity.scheduling.schedulers.PeriodicScheduler;
import uk.co.elionline.gears.entity.scheduling.terminating.schedulers.LinearScheduler;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentBuilder;
import uk.co.elionline.gears.entity.state.impl.StateComponentBuilderImpl;
import uk.co.elionline.gears.mathematics.expressions.IdentityExpression;
import uk.co.elionline.gears.mathematics.geometry.matrices.Vector2;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.utilities.CopyFactory;
import uk.co.elionline.gears.utilities.Factory;

public class Test1 {
	private final EntityManager entityManager;
	private final BehaviourComponentBuilder behaviourComponentBuilderFactory;
	private final StateComponentBuilder stateComponentBuilder;
	private final Assembler assembler;

	public Test1() {
		entityManager = new EntityManagerImpl();

		behaviourComponentBuilderFactory = new BehaviourComponentBuilderImpl();
		stateComponentBuilder = new StateComponentBuilderImpl();

		assembler = new AssemblerImpl();
	}

	public EntityManager entities() {
		return entityManager;
	}

	public Assembler assembler() {
		return assembler;
	}

	public StateComponentBuilder stateBuilder() {
		return stateComponentBuilder;
	}

	public BehaviourComponentBuilder behaviourBuilder() {
		return behaviourComponentBuilderFactory;
	}

	private void run() {
		PeriodicScheduler scheduler = new PeriodicScheduler(new LinearScheduler());
		scheduler.setPeriodFrequency(5);
		entities().behaviour().setDefaultScheduler(scheduler);

		final StateComponent<Vector2<DoubleValue>> position = stateBuilder()
				.dataFactory(new CopyFactory<>(new Vector2<>(DoubleValue.factory())))
				.name("Position").description("Position of entity").create();

		final StateComponent<Vector2<DoubleValue>> velocity = stateBuilder()
				.dataFactory(new CopyFactory<>(new Vector2<>(DoubleValue.factory())))
				.name("Velocity").description("Velocity of entity")
				.readDependencies(position).create();

		final StateComponent<IdentityExpression<String>> parrot = stateBuilder()
				.dataFactory(new Factory<IdentityExpression<String>>() {
					@Override
					public IdentityExpression<String> create() {
						return new IdentityExpression<>("");
					}
				}).name("Parrot").description("A parrot which knows a word").create();

		final BehaviourComponent movement = behaviourBuilder()
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext context) {
						for (Entity entity : context.getEntities()) {
							EntityStateManager state = context.entities().state();

							System.out.println(state.getData(entity, position).add(
									state.getData(entity, velocity)));
						}
					}
				}).name("Movement").description("Moves entity by velocity")
				.readDependencies(velocity).writeDependencies(position).create();
		entities().behaviour().addUniversal(movement);

		final BehaviourComponent parrotting = behaviourBuilder()
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext context) {
						for (Entity entity : context.getEntities()) {
							EntityStateManager state = context.entities().state();

							System.out.println(state.getData(entity, parrot).get());
						}
					}
				}).name("Parrotting").description("Parrot makes a noise")
				.readDependencies(parrot).create();
		entities().behaviour().addUniversal(parrotting);

		final Assemblage assemblage1 = assembler().create();
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

		final Assemblage assemblage2 = assembler().create();
		assemblage2.getBaseAssemblages().add(assemblage1);
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
		assemblage2.getSubassemblages().add(assemblage1);

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

		assembler().assemble(assemblage2, entities());
		assembler().assemble(assemblage2, entities());

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
