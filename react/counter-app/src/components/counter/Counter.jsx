import CounterButton from '../button/CounterButton';
import ResetButton from '../button/ResetButton';
import { useState } from 'react';
import '../../styles/Counter.css';

export default function Counter() {
    const [count, setCount] = useState(0);

    function handleCountChange(value) {
        setCount((prev) => prev + value);
    }

    function reset() {
        setCount(0);
    }

    const buttons = [
        { label: "+1", value: 1 },
        { label: "+3", value: 3 },
        { label: "+5", value: 5 },
        { label: "-1", value: -1 },
        { label: "-3", value: -3 },
        { label: "-5", value: -5 },
    ];

    return (
        <div>
            <span className="totalCount">{count}</span>
            <div className="buttonContainer">
                {buttons.map((button, index) => (
                    <CounterButton
                        key={index}
                        label={button.label}
                        value={button.value}
                        onClick={handleCountChange}
                    />
                ))}
            </div>
            <ResetButton onClick={reset} />
        </div>
    );
}