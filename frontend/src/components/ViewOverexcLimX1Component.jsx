import React, { Component } from 'react'
import OverexcLimX1Service from '../services/OverexcLimX1Service'

class ViewOverexcLimX1Component extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            overexcLimX1: {}
        }
    }

    componentDidMount(){
        OverexcLimX1Service.getOverexcLimX1ById(this.state.id).then( res => {
            this.setState({overexcLimX1: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View OverexcLimX1 Details</h3>
                    <div className = "card-body">
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewOverexcLimX1Component
