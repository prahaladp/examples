function [theta, J_history] = gradientDescent(X, y, theta, alpha, num_iters)
%GRADIENTDESCENT Performs gradient descent to learn theta
%   theta = GRADIENTDESCENT(X, y, theta, alpha, num_iters) updates theta by 
%   taking num_iters gradient steps with learning rate alpha

% Initialize some useful values
m = length(y); % number of training examples
J_history = zeros(num_iters, 1);
fprintf('theta = %f', theta);
fprintf('numiter = %d', num_iters);
for iter = 1:num_iters

    % ====================== YOUR CODE HERE ======================
    % Instructions: Perform a single gradient step on the parameter vector
    %               theta. 
    %
    % Hint: While debugging, it can be useful to print out the values
    %       of the cost function (computeCost) and gradient here.
    %

    Xmod = X(:,2);
    temp0 = theta(1) - alpha * sum(X * theta - y) / m;
    temp1 = theta(2) - alpha * sum((X * theta - y) .* Xmod) / m;

    theta = [temp0; temp1]

    % Save the cost J in every iteration    
    J_history(iter) = computeCost(X, y, theta);


    % ============================================================


end

end