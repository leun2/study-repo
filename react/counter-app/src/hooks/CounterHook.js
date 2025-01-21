import { useState } from "react";

export default function CounterHook(initialValue) {

    const [count, setCount] = useState(initialValue);

    function increment(value) {
        setCount((prev) => prev + value);
    }

    function decrement(value) {
        setCount((prev) => prev - value);
    }

    function reset() {
        setCount(initialValue);
    }

    return {
        count,
        increment,
        decrement,
        reset,
    };
}