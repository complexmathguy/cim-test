import React, { Component } from 'react'
import ExcST4BService from '../services/ExcST4BService'

class ListExcST4BComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                excST4Bs: []
        }
        this.addExcST4B = this.addExcST4B.bind(this);
        this.editExcST4B = this.editExcST4B.bind(this);
        this.deleteExcST4B = this.deleteExcST4B.bind(this);
    }

    deleteExcST4B(id){
        ExcST4BService.deleteExcST4B(id).then( res => {
            this.setState({excST4Bs: this.state.excST4Bs.filter(excST4B => excST4B.excST4BId !== id)});
        });
    }
    viewExcST4B(id){
        this.props.history.push(`/view-excST4B/${id}`);
    }
    editExcST4B(id){
        this.props.history.push(`/add-excST4B/${id}`);
    }

    componentDidMount(){
        ExcST4BService.getExcST4Bs().then((res) => {
            this.setState({ excST4Bs: res.data});
        });
    }

    addExcST4B(){
        this.props.history.push('/add-excST4B/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">ExcST4B List</h2>
                 <div className = "row">
                    <button className="btn btn-primary btn-sm" onClick={this.addExcST4B}> Add ExcST4B</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.excST4Bs.map(
                                        excST4B => 
                                        <tr key = {excST4B.excST4BId}>
                                             <td>
                                                 <button onClick={ () => this.editExcST4B(excST4B.excST4BId)} className="btn btn-info btn-sm">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteExcST4B(excST4B.excST4BId)} className="btn btn-danger btn-sm">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewExcST4B(excST4B.excST4BId)} className="btn btn-info btn-sm">View </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default ListExcST4BComponent
