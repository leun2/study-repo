import PropTypes from 'prop-types';
import '../../styles/Counter.css';

export default function ResetButton({ onClick }) {
    return (
        <button className="resetButton" onClick={onClick}>
            Reset
        </button>
    );
}

ResetButton.propTypes = {
    onClick: PropTypes.func.isRequired,
};