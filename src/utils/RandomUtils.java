package utils;

import models.*;
import models.alien.Alien;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class RandomUtils {
    public static void runModeRandomize(RunModeState state, Room room, Rectangle target) {
        var objs = room.getObjects().stream();
        var others = Arrays.stream(new Rectangle[]{state.getPlayer(), state.getDoor()});
        if (target == state.getPlayer()) others = Arrays.stream(new Rectangle[]{state.getDoor()});
        var shooters = state.getShooterAliens().stream().filter(s -> !s.equals(target));
        var blinds = state.getBlindAliens().stream().filter(b -> !b.equals(target));
        var wasters = state.getTimeWastingAliens().stream().filter(w -> !w.equals(target));
        var aliens = Stream.of(shooters, blinds, wasters).reduce(Stream::concat).orElseGet(Stream::empty);
        var powerUps = state.getPowerUps().stream();
        var avoid = Stream.of(objs, others, aliens, powerUps).reduce(Stream::concat).orElseGet(Stream::empty).toArray(Rectangle[]::new);
        RandomUtils.randomizePosition(state.getWidth(), state.getHeight(), target, avoid);
    }

    public static void buildModeRandomize(BuildModeState state, Room room, Rectangle target) {
        var objs = room.getObjects().stream();
        var others = Arrays.stream(new Rectangle[]{state.getDoor(), new Player(Constants.STARTING_LIVES, Constants.STARTING_X, Constants.STARTING_Y, Constants.PLAYER_DIM, Constants.PLAYER_DIM)});
        var avoid = Stream.concat(objs, others).toArray(Rectangle[]::new);
        RandomUtils.randomizePosition(state.getWidth(), state.getHeight(), target, avoid);
    }

    public static void randomizePosition(int roomWidth, int roomHeight, Rectangle target, Rectangle... avoid) {
        var random = new Random();
        var done = false;

        while (!done) {
            int x = random.nextInt(roomWidth - target.getWidth());
            int y = random.nextInt(roomHeight - target.getHeight());

            target.setPosition(new Position(x, y));

            int tooClose = Arrays.stream(avoid)
                    .map(target::distanceTo)
                    .mapToInt(b -> (b < Constants.MIN_DISTANCE) ? 1 : 0)
                    .sum();

            done = tooClose == 0;
        }
    }
}
