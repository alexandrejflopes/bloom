import React from 'react';


/*
* return the provided text with the first letter in uppercase
* */
class UcFirst extends React.Component {
    render() {
        const string = this.props.text;
        return string.charAt(0).toUpperCase() + string.slice(1);
    }
}

export default UcFirst;