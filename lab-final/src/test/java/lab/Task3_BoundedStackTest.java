package lab;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════
 *  TASK 3 – Writing Properties for BoundedStack<T>  (40 min)
 * ═══════════════════════════════════════════════════════════════════
 *
 *  BoundedStack is a generic stack with a fixed maximum capacity.
 *  Its methods: push(item), pop(), peek(), size(), isEmpty(), isFull()
 *
 *  PART A (Guided) – Complete properties 1, 2, and 3.
 *                    Follow the hints provided.
 *
 *  PART B (Open)   – Design and implement properties 4 and 5 yourself.
 *                    No hints given. Think about what else must be true.
 */
public class Task3_BoundedStackTest {

    // ════════════════════════════════════════════════════════════════
    //  PART A – GUIDED
    // ════════════════════════════════════════════════════════════════

    // ── PROPERTY 1: LIFO ordering ────────────────────────────────────
    //
    // After pushing a list of integers onto the stack,
    // popping them one by one must yield the reverse of the input list.
    //
    @Property
    void lifoOrdering(
            @ForAll @Size(min = 1, max = 50)
            List<@IntRange(min = -1000, max = 1000) Integer> items) {

        BoundedStack<Integer> stack = new BoundedStack<>(items.size());

        // TODO step 1: push all items onto the stack

        // TODO step 2: pop all items into a new list

        // TODO step 3: assert that the popped list equals the REVERSE of items
        //              Hint: Collections.reverse() or new ArrayList<>(items) + reverse

    }

    // ── PROPERTY 2: size tracking ────────────────────────────────────
    //
    // The size() after pushing n items must equal n.
    // Also verify size() after each individual push (not just at the end).
    //
    @Property
    void sizeTracksNumberOfPushes(
            @ForAll @IntRange(min = 1, max = 100) int n) {

        BoundedStack<Integer> stack = new BoundedStack<>(n);

        for (int i = 0; i < n; i++) {
            // TODO: assert size before push, then push, then assert size after push
        }

        // TODO: final assertion — stack must be full

    }

    // ── PROPERTY 3: push-pop round trip ──────────────────────────────
    //
    // Pushing an element and immediately popping it must:
    //   (a) return the exact same element
    //   (b) leave the stack's size unchanged
    //
    @Property
    void pushPopRoundTrip(
            @ForAll @Size(min = 0, max = 49)
            List<@IntRange(min = -500, max = 500) Integer> initial,
            @ForAll @IntRange(min = -500, max = 500) int extra) {

        BoundedStack<Integer> stack = new BoundedStack<>(initial.size() + 1);
        initial.forEach(stack::push);

        int sizeBefore = stack.size();

        // TODO: push 'extra', then pop, then assert:
        //       1. the popped value equals 'extra'
        //       2. the size returned to sizeBefore

    }

    // ════════════════════════════════════════════════════════════════
    //  PART B – OPEN (no hints)
    // ════════════════════════════════════════════════════════════════

    // ── PROPERTY 4 ───────────────────────────────────────────────────
    //
    // TODO: Write a property of your own choice.
    //       State what you are testing in the comment below.
    //
    // WHAT THIS PROPERTY TESTS:
    //
    @Property
    void property4(/* TODO: add parameters */) {

        // TODO: implement

    }

    // ── PROPERTY 5 ───────────────────────────────────────────────────
    //
    // TODO: Write another property.
    //       At least one of properties 4 or 5 must involve an
    //       EXCEPTION being thrown (full or empty stack).
    //
    // WHAT THIS PROPERTY TESTS:
    //
    @Property
    void property5(/* TODO: add parameters */) {

        // TODO: implement

    }
}
